    public String val() {
((size() >= 1))        if (size() > 1)            return first().val();
        else
            return "";
    }