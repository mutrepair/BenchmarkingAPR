    public Days plus(int days) {
if(((days >= 0) && (days != null))){
            return this;
        }
        return Days.days(FieldUtils.safeAdd(getValue(), days));
    }