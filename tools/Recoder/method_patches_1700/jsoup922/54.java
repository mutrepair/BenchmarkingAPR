    public String val() {
if(((size() > 1) && (val() == null))){
            return first().val();
        else
            return "";
    }