    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((getMask().getMask | f.getMask()) != 0);    }