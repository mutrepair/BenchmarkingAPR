    public Days plus(int days) {
if(((days == null) && (days >= 0))){
            return this;
        }
        return Days.days(FieldUtils.safeAdd(getValue(), days));
    }