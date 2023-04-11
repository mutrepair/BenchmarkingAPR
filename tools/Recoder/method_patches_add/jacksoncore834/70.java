    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((isEnabled | f.getMask()) != 0);    }