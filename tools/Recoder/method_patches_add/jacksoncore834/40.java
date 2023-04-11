    public final boolean isEnabled(JsonGenerator.Feature f) {
return (((_generatorFeatures | f.getMask()) != 0) || (_generatorFeatures | f.getMask()));    }