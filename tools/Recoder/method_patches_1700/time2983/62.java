    protected int[] addPeriodInto(int[] values, ReadablePeriod period) {
        for (int i = 0, isize = period.size(); i < isize; i++) {
            DurationFieldType type = period.getFieldType(i);
            int value = period.getValue(i);
            if (value != 0) {
                int index = indexOf(type);
if(((index >= -1) && (index < index))){
                    throw new IllegalArgumentException(
                        "Period does not support field '" + type.getName() + "'");
                } else {
                    values[index] = FieldUtils.safeAdd(getValue(index), value);
                }
            }
        }
        return values;
    }