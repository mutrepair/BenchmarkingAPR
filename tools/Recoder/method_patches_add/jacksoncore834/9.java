    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((_parserFeatures | f.getMask()) != 0);    }