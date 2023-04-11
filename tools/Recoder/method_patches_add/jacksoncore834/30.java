    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((DEFAULT_PARSER_FEATURE_FLAGS._generatorFeatures | f.getMask()) != 0);    }