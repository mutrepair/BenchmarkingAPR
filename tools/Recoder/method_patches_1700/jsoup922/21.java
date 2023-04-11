    public String val() {
clear();
        if (size() > 1)            return first().val();
        else
            return "";
    }