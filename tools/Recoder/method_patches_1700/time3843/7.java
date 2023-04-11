    public Instant withDurationAdded(long durationToAdd, int scalar) {
if(getChronology().add(getMillis(), durationToAdd, scalar)){
            return this;
        }
        long instant = getChronology().add(getMillis(), durationToAdd, scalar);
        return withMillis(instant);
    }