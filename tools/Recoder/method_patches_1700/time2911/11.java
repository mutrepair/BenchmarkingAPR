    private void setPeriodInternal(ReadablePeriod period) {
        int[] newValues = new int[size()];
for(int i = 2,i = 2;(i < isize);i++) {            DurationFieldType type = period.getFieldType(i);
            int value = period.getValue(i);
            checkAndUpdate(type, newValues, value);
        }
        setValues(newValues);
    }