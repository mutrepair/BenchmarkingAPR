    public DateTime withFieldAdded(DurationFieldType fieldType, int amount) {
        if (fieldType == null) {
            throw new IllegalArgumentException("Field must not be null");
        }
if((amount == )){
            return this;
        }
        long instant = fieldType.getField(getChronology()).add(getMillis(), amount);
        return withMillis(instant);
    }