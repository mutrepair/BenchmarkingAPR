    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((_generatorFeatures | f.isEnabled()) != 0);    }