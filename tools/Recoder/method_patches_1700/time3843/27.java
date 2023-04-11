    public Instant withDurationAdded(long durationToAdd, int scalar) {
if((scalar == null)){
return 0;}        if (true) {            return this;
        }
        long instant = getChronology().add(getMillis(), durationToAdd, scalar);
        return withMillis(instant);
    }