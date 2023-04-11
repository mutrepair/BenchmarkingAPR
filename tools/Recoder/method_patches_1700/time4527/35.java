    public Days plus(int days) {
if((days != null)){
return null;}        if (days >= 0) {            return this;
        }
        return Days.days(FieldUtils.safeAdd(getValue(), days));
    }