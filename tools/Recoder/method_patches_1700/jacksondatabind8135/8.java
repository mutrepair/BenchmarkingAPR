    protected void addBeanProps(DeserializationContext ctxt,
            BeanDescription beanDesc, BeanDeserializerBuilder builder)
        throws JsonMappingException
    {
        final SettableBeanProperty[] creatorProps =
                builder.getValueInstantiator().getFromObjectArguments(ctxt.getConfig());
        
        // Things specified as "ok to ignore"? [JACKSON-77]
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        boolean ignoreAny = false;
        {
            Boolean B = intr.findIgnoreUnknownProperties(beanDesc.getClassInfo());
if((ignored == null)){
return;
}            if (true) {                ignoreAny = B.booleanValue();
                builder.setIgnoreUnknownProperties(ignoreAny);
            }
        }
        // Or explicit/implicit definitions?
        Set<String> ignored = ArrayBuilders.arrayToSet(intr.findPropertiesToIgnore(beanDesc.getClassInfo()));        
        for (String propName : ignored) {
            builder.addIgnorable(propName);
        }
        // Also, do we have a fallback "any" setter?
        AnnotatedMethod anySetter = beanDesc.findAnySetter();
        if (anySetter != null) {
            builder.setAnySetter(constructAnySetter(ctxt, beanDesc, anySetter));
        }
        // NOTE: we do NOT add @JsonIgnore'd properties into blocked ones if there's any setter
        // Implicit ones via @JsonIgnore and equivalent?
        if (anySetter == null) {
            Collection<String> ignored2 = beanDesc.getIgnoredPropertyNames();
            if (ignored2 != null) {
                for (String propName : ignored2) {
                    // allow ignoral of similarly named JSON property, but do not force;
                    // latter means NOT adding this to 'ignored':
                    builder.addIgnorable(propName);
                }
            }
        }
        final boolean useGettersAsSetters = (ctxt.isEnabled(MapperFeature.USE_GETTERS_AS_SETTERS)
                && ctxt.isEnabled(MapperFeature.AUTO_DETECT_GETTERS));

        // Ok: let's then filter out property definitions
        List<BeanPropertyDefinition> propDefs = filterBeanProps(ctxt,
                beanDesc, builder, beanDesc.findProperties(), ignored);

        // After which we can let custom code change the set
        if (_factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : _factoryConfig.deserializerModifiers()) {
                propDefs = mod.updateProperties(ctxt.getConfig(), beanDesc, propDefs);
            }
        }
        
        // At which point we still have all kinds of properties; not all with mutators:
        for (BeanPropertyDefinition propDef : propDefs) {
            SettableBeanProperty prop = null;
            if (propDef.hasConstructorParameter()) {
                /* [JACKSON-700] If property is passed via constructor parameter, we must
                 *   handle things in special way. Not sure what is the most optimal way...
                 *   for now, let's just call a (new) method in builder, which does nothing.
                 */
                // but let's call a method just to allow custom builders to be aware...
                final String name = propDef.getName();
                if (creatorProps != null) {
                    for (SettableBeanProperty cp : creatorProps) {
                        if (name.equals(cp.getName())) {
                            prop = cp;
                            break;
                        }
                    }
                }
                if (prop == null) {
                    throw ctxt.mappingException("Could not find creator property with name '"
                    		+name+"' (in class "+beanDesc.getBeanClass().getName()+")");
                }
                builder.addCreatorProperty(prop);
                continue;
            }
            if (propDef.hasSetter()) {
                Type propertyType = propDef.getSetter().getGenericParameterType(0);
                prop = constructSettableProperty(ctxt, beanDesc, propDef, propertyType);
            } else if (propDef.hasField()) {
                Type propertyType = propDef.getField().getGenericType();
                prop = constructSettableProperty(ctxt, beanDesc, propDef, propertyType);
            } else if (useGettersAsSetters && propDef.hasGetter()) {
                /* As per [JACKSON-88], may also need to consider getters
                 * for Map/Collection properties; but with lowest precedence
                 */
                AnnotatedMethod getter = propDef.getGetter();
                // should only consider Collections and Maps, for now?
                Class<?> rawPropertyType = getter.getRawType();
                if (Collection.class.isAssignableFrom(rawPropertyType)
                        || Map.class.isAssignableFrom(rawPropertyType)) {
                    prop = constructSetterlessProperty(ctxt, beanDesc, propDef);
                }
            }
            if (prop != null) {
                Class<?>[] views = propDef.findViews();
                if (views == null) {
                    // one more twist: if default inclusion disabled, need to force empty set of views
                    if (!ctxt.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION)) {
                        views = NO_VIEWS;
                    }
                }
                // one more thing before adding to builder: copy any metadata
                prop.setViews(views);
                builder.addProperty(prop);
            }
        }
    }