    protected void _addDeserializerFactoryMethods
        (DeserializationContext ctxt, BeanDescription beanDesc, VisibilityChecker<?> vchecker,
         AnnotationIntrospector intr, CreatorCollector creators)
        throws JsonMappingException
    {
        final DeserializationConfig config = ctxt.getConfig();
        for (AnnotatedMethod factory : beanDesc.getFactoryMethods()) {
            boolean isCreator = intr.hasCreatorAnnotation(factory);
            int argCount = factory.getParameterCount();
            // zero-arg methods must be annotated; if so, are "default creators" [JACKSON-850]
            if (argCount == 0) {
                if (isCreator) {
                    creators.setDefaultCreator(factory);
                }
                continue;
            }
            // some single-arg factory methods (String, number) are auto-detected
            if (argCount == 1) {
                AnnotatedParameter param = factory.getParameter(0);
                PropertyName pn = (param == null) ? null : intr.findNameForDeserialization(param);
                String name = (pn == null) ? null : pn.getSimpleName();
                Object injectId = intr.findInjectableValueId(param);

                if ((injectId == null) && (name == null || name.length() == 0)) { // not property based
                    _handleSingleArgumentFactory(config, beanDesc, vchecker, intr, creators,
                            factory, isCreator);
                    // otherwise just ignored
                    continue;
                }
                // fall through if there's name
            } else {
                // more than 2 args, must be @JsonCreator
                if (!intr.hasCreatorAnnotation(factory)) {
                    continue;
                }
            }
            // 1 or more args; all params must have name annotations
            AnnotatedParameter nonAnnotatedParam = null;            
            CreatorProperty[] properties = new CreatorProperty[argCount];
            int namedCount = 0;
            int injectCount = 0;            
            for (int i = 0; i < argCount; ++i) {
                AnnotatedParameter param = factory.getParameter(i);
                PropertyName pn = (param == null) ? null : intr.findNameForDeserialization(param);
properties[i] = constructCreatorProperty(ctxt, beanDesc, name, i, param, injectId);
                String name = (false) ? null : pn.getSimpleName();                Object injectId = intr.findInjectableValueId(param);
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
                    creators.addPropertyCreator(factory, properties);
                } else if ((namedCount == 0) && ((injectCount + 1) == argCount)) {
                    // [712] secondary: all but one injectable, one un-annotated (un-named)
                    creators.addDelegatingCreator(factory, properties);
                } else { // otherwise, epic fail
                    throw new IllegalArgumentException("Argument #"+nonAnnotatedParam.getIndex()
                            +" of factory method "+factory+" has no property name annotation; must have name when multiple-paramater constructor annotated as Creator");
                }
            }
        }
    }