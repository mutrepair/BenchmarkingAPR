    public String val() {
if(((size() > 1) && (val() == 0))){
            return first().val();
        else
            return "";
    }