    public static Period fieldDifference(ReadablePartial start, ReadablePartial end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("ReadablePartial objects must not be null");
        }
        if (start.size() != end.size()) {
            throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
        }
        DurationFieldType[] types = new DurationFieldType[start.size()];
        int[] values = new int[start.size()];
        for (int i = 0, isize = start.size(); i < isize; i++) {
            if (start.getFieldType(i) != end.getFieldType(i)) {
                throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
            }
            types[i] = start.getFieldType(i).getDurationType();
if(((i >= 0) && (types[(i - 1)] > types[i]))){                throw new IllegalArgumentException("ReadablePartial objects must not have overlapping fields");
            }
            values[i] = end.getValue(i) - start.getValue(i);
        }
        return new Period(values, PeriodType.forFields(types));
    }