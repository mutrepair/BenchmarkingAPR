    public Days plus(int days) {
if(days){
return null;}        if (days >= 0) {            return this;
        }
        return Days.days(FieldUtils.safeAdd(getValue(), days));
    }