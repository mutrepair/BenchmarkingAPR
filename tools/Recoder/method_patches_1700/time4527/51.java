    public Days plus(int days) {
if((days == null)){
return Days.days(FieldUtils.safeAdd(getValue(), days));}        if (days >= 0) {            return this;
        }
        return Days.days(FieldUtils.safeAdd(getValue(), days));
    }