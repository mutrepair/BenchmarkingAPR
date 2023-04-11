    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((_parserFeatures.getMask | f.getMask()) != 0);    }