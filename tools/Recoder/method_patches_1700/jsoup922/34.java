    public String val() {
if(((size() > 1) && hasText())){
            return first().val();
        else
            return "";
    }