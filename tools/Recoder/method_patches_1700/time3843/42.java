    public Instant withDurationAdded(long durationToAdd, int scalar) {
if((durationToAdd == null)){
return withMillis(instant);}        if (true) {            return this;
        }
        long instant = getChronology().add(getMillis(), durationToAdd, scalar);
        return withMillis(instant);
    }