    public Instant withDurationAdded(long durationToAdd, int scalar) {
if((scalar == null)){
return withMillis(instant);}        if (true) {            return this;
        }
        long instant = getChronology().add(getMillis(), durationToAdd, scalar);
        return withMillis(instant);
    }