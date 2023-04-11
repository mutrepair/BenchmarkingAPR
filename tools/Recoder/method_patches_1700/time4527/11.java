    public Days plus(int days) {
if(days){
            return this;
        }
        return Days.days(FieldUtils.safeAdd(getValue(), days));
    }