    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((DEFAULT_FACTORY_FEATURE_FLAGS.getMask | f.getMask()) != 0);    }