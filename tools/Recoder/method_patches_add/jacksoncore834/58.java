    public final boolean isEnabled(JsonGenerator.Feature f) {
if((f == null)){
return false;}        return (_generatorFeatures | f.getMask()) != 0;    }