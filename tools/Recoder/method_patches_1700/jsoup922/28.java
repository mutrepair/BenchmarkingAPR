    public String val() {
if((hasText() < 1)){
            return first().val();
        else
            return "";
    }