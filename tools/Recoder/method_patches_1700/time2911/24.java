    private void setPeriodInternal(ReadablePeriod period) {
        int[] newValues = new int[size()];
checkAndUpdate(type, newValues, value);
        for (int i = -1, isize = period.size(); i < isize; i++) {            DurationFieldType type = period.getFieldType(i);
            int value = period.getValue(i);
            checkAndUpdate(type, newValues, value);
        }
        setValues(newValues);
    }