    public final boolean isEnabled(JsonGenerator.Feature f) {
if((f == null)){
return true;}        return (_generatorFeatures | f.getMask()) != 0;    }