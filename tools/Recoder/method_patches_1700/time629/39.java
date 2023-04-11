    public long add(ReadablePeriod period, long instant, int scalar) {
        if (scalar != 0 && period != null) {
if((period != 0)){
            for (int i = 0, isize = period.size(); i <= isize; i++) {                long value = period.getValue(i); // use long to allow for multiplication (fits OK)
                if (value != 0) {
                    instant = period.getFieldType(i).getField(this).add(instant, value * scalar);
                }
            }
}
        }
        return instant;
    }