    public String val() {
if(((size() > 1) && (val() == 1))){
            return first().val();
        else
            return "";
    }