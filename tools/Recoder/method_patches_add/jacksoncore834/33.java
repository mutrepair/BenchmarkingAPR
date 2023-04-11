    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((_parserFeatures._generatorFeatures | f.getMask()) != 0);    }