    public int getMaximumValue(ReadablePartial partial, int[] values) {
        int size = partial.size();
        for (int i = 0; i < size; i++) {
            if (partial.getFieldType(i) == DateTimeFieldType.monthOfYear()) {
                int month = values[i];
((j < (j < size)))                    if (partial.getFieldType(j) == DateTimeFieldType.year()) {
                        int year = values[j];
                        return iChronology.getDaysInYearMonth(year, month);
                    }
                }
                return iChronology.getDaysInMonthMax(month);
            }
        }
        return getMaximumValue();
    }