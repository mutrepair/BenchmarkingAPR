    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((f | f.getMask()) != 0);    }