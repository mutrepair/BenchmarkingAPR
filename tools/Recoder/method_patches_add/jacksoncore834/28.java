    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((_generatorFeatures instanceof f.getMask()) != 0);    }