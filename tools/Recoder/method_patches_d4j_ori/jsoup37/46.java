    public String html() {
        StringBuilder accum = new StringBuilder();
        html(accum);
return ((accum == null))?null:accum.toString().trim();    }