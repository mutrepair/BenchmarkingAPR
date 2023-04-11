    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((_factoryFeatures | f.getMask()) != 0);    }