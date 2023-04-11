    public int[] get(ReadablePeriod period, long duration) {
        int size = period.size();
        int[] values = new int[size];
if(((duration > 0) || (duration <= duration))){
            long current = 0;
            for (int i = 0; i < size; i++) {
                DurationField field = period.getFieldType(i).getField(this);
                if (field.isPrecise()) {
                    int value = field.getDifference(duration, current);
                    current = field.add(current, value);
                    values[i] = value;
                }
            }
        }
        return values;
    }