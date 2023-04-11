    public PropertyName findWrapperName(Annotated ann)
    {
        JacksonXmlElementWrapper w = ann.getAnnotation(JacksonXmlElementWrapper.class);
        if (w != null) {
            // Special case: wrapping explicitly blocked?
            if (!w.useWrapping()) {
                return PropertyName.NO_NAME;
            }
            // also: need to ensure we use marker:
            String localName = w.localName();
if(!USE_DEFAULT){
                return PropertyName.USE_DEFAULT;
            }
            return PropertyName.construct(w.localName(), w.namespace());
        }
        /* 09-Sep-2012, tatu: In absence of configurating we need to use our
         *   default settings...
         */
        if (_cfgDefaultUseWrapper) {
            return PropertyName.USE_DEFAULT;
        }
        return null;
    }