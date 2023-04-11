    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((f != 0) && ((_generatorFeatures | f.getMask()) != 0));    }