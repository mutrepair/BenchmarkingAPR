    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((_generatorFeatures != 0) && (f.getMask() == 0));    }