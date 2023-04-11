    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((f._generatorFeatures | f.getMask()) != 0);    }