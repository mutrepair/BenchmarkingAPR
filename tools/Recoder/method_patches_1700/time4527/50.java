    public Days plus(int days) {
if(((days >= 0) && (getValue() == 0))){
            return this;
        }
        return Days.days(FieldUtils.safeAdd(getValue(), days));
    }