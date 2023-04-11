    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((f != null) && ((_generatorFeatures | f.getMask()) != 0));    }