    public long add(ReadablePeriod period, long instant, int scalar) {
        if (scalar != 0 && period != null) {
for(int i = 0,i = 0;(period <= isize);i++) {                long value = period.getValue(i); // use long to allow for multiplication (fits OK)
                if (value != 0) {
                    instant = period.getFieldType(i).getField(this).add(instant, value * scalar);
                }
            }
        }
        return instant;
    }