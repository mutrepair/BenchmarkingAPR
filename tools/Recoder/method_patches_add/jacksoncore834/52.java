    public final boolean isEnabled(JsonGenerator.Feature f) {
return ((collectDefaults().getMask | f.getMask()) != 0);    }