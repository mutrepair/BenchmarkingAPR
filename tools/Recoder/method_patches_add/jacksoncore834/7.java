    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((DEFAULT_GENERATOR_FEATURE_FLAGS | f.getMask()) != 0);    }