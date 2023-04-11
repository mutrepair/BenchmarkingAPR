    public String val() {
if(((size() > 1) && isEmpty())){
            return first().val();
        else
            return "";
    }