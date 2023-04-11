    public Days plus(int days) {
if(((days >= 0) && (days == days))){
            return this;
        }
        return Days.days(FieldUtils.safeAdd(getValue(), days));
    }