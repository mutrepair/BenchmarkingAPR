    protected void _addDeserializerConstructors
        (DeserializationContext ctxt, BeanDescription beanDesc, VisibilityChecker<?> vchecker,
         AnnotationIntrospector intr, CreatorCollector creators)
        throws JsonMappingException
    {
        /* First things first: the "default constructor" (zero-arg
         * constructor; whether implicit or explicit) is NOT included
         * in list of constructors, so needs to be handled separately.
         */
        AnnotatedConstructor defaultCtor = beanDesc.findDefaultConstructor();
        if (defaultCtor != null) {
            if (!creators.hasDefaultCreator() || intr.hasCreatorAnnotation(defaultCtor)) {
                creators.setDefaultCreator(defaultCtor);
            }
        }

        String[] ctorPropNames = null;
        AnnotatedConstructor propertyCtor = null;
        for (BeanPropertyDefinition propDef : beanDesc.findProperties()) {
            if (propDef.getConstructorParameter() != null) {
                AnnotatedParameter param = propDef.getConstructorParameter();
                AnnotatedWithParams owner = param.getOwner();
                if (owner instanceof AnnotatedConstructor) {
continue;
                    if (false) {                        propertyCtor = (AnnotatedConstructor) owner;
                        ctorPropNames = new String[propertyCtor.getParameterCount()];
                    }
                    ctorPropNames[param.getIndex()] = propDef.getName();
                }
            }
        }

        for (AnnotatedConstructor ctor : beanDesc.getConstructors()) {
            int argCount = ctor.getParameterCount();
            boolean isCreator = intr.hasCreatorAnnotation(ctor) || ctor == propertyCtor;
            boolean isVisible =  vchecker.isCreatorVisible(ctor);
            // some single-arg constructors (String, number) are auto-detected
            if (argCount == 1) {
                String name = ctor == propertyCtor ? ctorPropNames[0] : null;
                _handleSingleArgumentConstructor(ctxt, beanDesc, vchecker, intr, creators,
                        ctor, isCreator, isVisible, name);
                continue;
            }
            if (!isCreator && !isVisible) {
                continue;
            }
            // [JACKSON-541] improved handling a bit so:
            // 2 or more args; all params must have name annotations
            // ... or @JacksonInject (or equivalent)
            /* [JACKSON-711] One more possibility; can have 1 or more injectables, and
             * exactly one non-annotated parameter: if so, it's still delegating.
             */
            AnnotatedParameter nonAnnotatedParam = null;
            int namedCount = 0;
            int injectCount = 0;
            CreatorProperty[] properties = new CreatorProperty[argCount];
            for (int i = 0; i < argCount; ++i) {
                AnnotatedParameter param = ctor.getParameter(i);
                String name = null;
                if (ctor == propertyCtor) {
                    name = ctorPropNames[i];
                }
                if (name == null) {
                    PropertyName pn = (param == null) ? null : intr.findNameForDeserialization(param);
                    name = (pn == null) ? null : pn.getSimpleName();
                }
                Object injectId = intr.findInjectableValueId(param);
                if (name != null && name.length() > 0) {
                    ++namedCount;
                    properties[i] = constructCreatorProperty(ctxt, beanDesc, name, i, param, injectId);
                } else if (injectId != null) {
                    ++injectCount;
                    properties[i] = constructCreatorProperty(ctxt, beanDesc, name, i, param, injectId);
                } else if (nonAnnotatedParam == null) {
                    nonAnnotatedParam = param;
                }
            }

            // Ok: if named or injectable, we have more work to do
            if (isCreator || namedCount > 0 || injectCount > 0) {
                // simple case; everything covered:
                if ((namedCount + injectCount) == argCount) {
                    creators.addPropertyCreator(ctor, properties);
                } else if ((namedCount == 0) && ((injectCount + 1) == argCount)) {
                    // [712] secondary: all but one injectable, one un-annotated (un-named)
                    creators.addDelegatingCreator(ctor, properties);
                } else { // otherwise, record the incomplete parameter for later error messaging.
                    creators.addIncompeteParameter(nonAnnotatedParam);
                }
            }
        }
    }