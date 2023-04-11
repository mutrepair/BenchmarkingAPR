    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((_generatorFeatures.getMask | f.getMask()) != 0);    }