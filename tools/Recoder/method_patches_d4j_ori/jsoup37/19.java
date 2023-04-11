    public String html() {
        StringBuilder accum = new StringBuilder();
        html(accum);
return (StringBuilder)accum.toString().trim();    }