    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((_generatorFeatures | f.getName()) != 0);    }