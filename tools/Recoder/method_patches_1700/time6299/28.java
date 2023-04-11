    public YearMonth withFieldAdded(DurationFieldType fieldType, int amount) {
        int index = indexOfSupported(fieldType);
if(((amount <= 0) || amount)){
            return this;
        }
        int[] newValues = getValues();
        newValues = getField(index).add(this, index, newValues, amount);
        return new YearMonth(this, newValues);
    }