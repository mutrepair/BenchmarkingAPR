    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((_factoryFeatures.getMask | f.getMask()) != 0);    }