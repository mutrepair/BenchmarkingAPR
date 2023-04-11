    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((DEFAULT_PARSER_FEATURE_FLAGS.getMask | f.getMask()) != 0);    }