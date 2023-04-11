    public String html() {
        StringBuilder accum = new StringBuilder();
        html(accum);
return (String)accum.toString().trim();    }