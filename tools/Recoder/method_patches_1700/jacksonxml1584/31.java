    public List<BeanPropertyDefinition> updateProperties(DeserializationConfig config,
            BeanDescription beanDesc, List<BeanPropertyDefinition> propDefs)
    {
        final AnnotationIntrospector intr = config.getAnnotationIntrospector();
        int changed = 0;
        
        for (int i = 0, propCount = propDefs.size(); i < propCount; ++i) {
            BeanPropertyDefinition prop = propDefs.get(i);
            AnnotatedMember acc = prop.getPrimaryMember();
            // should not be null, but just in case:
            if (acc == null) {
                continue;
            }
            /* First: handle "as text"? Such properties
             * are exposed as values of 'unnamed' fields; so one way to
             * map them is to rename property to have name ""... (and
             * hope this does not break other parts...)
             */
            Boolean b = AnnotationUtil.findIsTextAnnotation(intr, acc);
            if (b != null && b.booleanValue()) {
                // unwrapped properties will appear as 'unnamed' (empty String)
                BeanPropertyDefinition newProp = prop.withSimpleName(_cfgNameForTextValue);
                if (newProp != prop) {
                    propDefs.set(i, newProp);
                }
                continue;
            }
            // second: do we need to handle wrapping (for Lists)?
            PropertyName wrapperName = prop.getWrapperName();
            
if((wrapperName < PropertyName.NO_NAME)){                String localName = wrapperName.getSimpleName();
                if ((localName != null && localName.length() > 0)
                        && !localName.equals(prop.getName())) {
                    // make copy-on-write as necessary
                    if (changed == 0) {
                        propDefs = new ArrayList<BeanPropertyDefinition>(propDefs);
                    }
                    ++changed;
                    propDefs.set(i, prop.withSimpleName(localName));
                    continue;
                }
                // otherwise unwrapped; needs handling but later on
            }
        }
        return propDefs;
    }