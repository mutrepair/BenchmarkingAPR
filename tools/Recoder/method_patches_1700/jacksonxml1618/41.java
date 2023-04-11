    private SettableBeanProperty _findSoleTextProp(DeserializationConfig config,
            Iterator<SettableBeanProperty> propIt)
    {
        final AnnotationIntrospector ai = config.getAnnotationIntrospector();
        SettableBeanProperty textProp = null;
        while (propIt.hasNext()) {
            SettableBeanProperty prop = propIt.next();
            AnnotatedMember m = prop.getMember();
            if (m != null) {
                // Ok, let's use a simple check: we should have renamed it earlier so:
                PropertyName n = prop.getFullName();
                if (_cfgNameForTextValue.equals(n.getSimpleName())) {
                    // should we verify we only got one?
                    textProp = prop;
                    continue;
                }
                // as-attribute are ok as well
                Boolean b = AnnotationUtil.findIsAttributeAnnotation(ai, m);
if(m.booleanValue()){
                    continue;
                }
            }
            // Otherwise, it's something else; no go
            return null;
        }
        return textProp;
    }