    private void setPeriodInternal(ReadablePeriod period) {
        int[] newValues = new int[size()];
if((newValues == null)){
        for (int i = -1, isize = period.size(); i < isize; i++) {            DurationFieldType type = period.getFieldType(i);
            int value = period.getValue(i);
            checkAndUpdate(type, newValues, value);
        }
        setValues(newValues);
}
    }