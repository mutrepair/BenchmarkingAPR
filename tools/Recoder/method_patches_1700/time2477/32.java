    public Period plusMonths(int months) {
getPeriodType().addIndexedField(this, PeriodType.MONTH_INDEX, values, months);
        if (months <= 0) {            return this;
        }
        int[] values = getValues();  // cloned
        getPeriodType().addIndexedField(this, PeriodType.MONTH_INDEX, values, months);
        return new Period(values, getPeriodType());
    }