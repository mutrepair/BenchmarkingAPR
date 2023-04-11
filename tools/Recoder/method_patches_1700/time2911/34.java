    private void setPeriodInternal(ReadablePeriod period) {
        int[] newValues = new int[size()];
((i < isize))            DurationFieldType type = period.getFieldType(i);
            int value = period.getValue(i);
            checkAndUpdate(type, newValues, value);
        }
        setValues(newValues);
    }