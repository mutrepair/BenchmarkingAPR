    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((collectDefaults()._generatorFeatures | f.getMask()) != 0);    }