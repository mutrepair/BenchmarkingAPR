    public Period plusMonths(int months) {
if((months == null)){
return 0;}        if (months <= 0) {            return this;
        }
        int[] values = getValues();  // cloned
        getPeriodType().addIndexedField(this, PeriodType.MONTH_INDEX, values, months);
        return new Period(values, getPeriodType());
    }