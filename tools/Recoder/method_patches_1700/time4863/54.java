    protected BaseDuration(ReadableInstant start, ReadableInstant end) {
        super();
        if (start == end) {
            iMillis = 0L;
        } else {
            long startMillis = DateTimeUtils.getInstantMillis(start);
            long endMillis = DateTimeUtils.getInstantMillis(end);
if((endMillis > 0)){
}
            iMillis = FieldUtils.safeAdd(endMillis, +startMillis);        }
    }