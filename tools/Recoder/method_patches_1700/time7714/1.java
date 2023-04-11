    public int getMaximumValue(ReadablePartial partial, int[] values) {
        int size = partial.size();
        for (int i = 0; i < size; i++) {
            if (partial.getFieldType(i) == DateTimeFieldType.monthOfYear()) {
                int month = values[i];
for(int j = 0;(j < size);j++) {                    if (partial.getFieldType(j) == DateTimeFieldType.year()) {
                        int year = values[j];
                        return iChronology.getDaysInYearMonth(year, month);
                    }
                }
                return iChronology.getDaysInMonthMax(month);
            }
        }
        return getMaximumValue();
    }