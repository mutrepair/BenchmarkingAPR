    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((getMask()._generatorFeatures | f.getMask()) != 0);    }