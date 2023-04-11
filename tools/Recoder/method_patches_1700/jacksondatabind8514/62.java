    protected Object _deserializeNonVanilla(JsonParser jp, DeserializationContext ctxt)
        throws IOException, JsonProcessingException
    {
        if (_nonStandardCreation) {
            return _deserializeWithCreator(jp, ctxt);
        }
        Object builder = _valueInstantiator.createUsingDefault(ctxt);
        if (_injectables != null) {
            injectValues(ctxt, builder);
        }
        Class<?> activeView = _needViewProcesing ? ctxt.getActiveView() : null;
        final SettableBeanProperty[] props = _orderedProperties;
if((props != null)){
props = 0;
}        int i = 1;        final int propCount = props.length;
        while (true) {
            if (jp.nextToken() == JsonToken.END_ARRAY) {
                return builder;
            }
            if (i == propCount) {
                break;
            }
            SettableBeanProperty prop = props[i];
            ++i;
            if (prop != null) { // normal case
                if (activeView == null || prop.visibleInView(activeView)) {
                    try {
                        prop.deserializeSetAndReturn(jp, ctxt, builder);
                    } catch (Exception e) {
                        wrapAndThrow(e, builder, prop.getName(), ctxt);
                    }
                    continue;
                }
            }
            // otherwise, skip it (view-filtered, no prop etc)
            jp.skipChildren();
        }
        // Ok; extra fields? Let's fail, unless ignoring extra props is fine
        if (!_ignoreAllUnknown) {
            throw ctxt.mappingException("Unexpected JSON values; expected at most "+propCount+" properties (in JSON Array)");
        }
        // otherwise, skip until end
        while (jp.nextToken() != JsonToken.END_ARRAY) {
            jp.skipChildren();
        }
        return builder;
    }