    public Instant withDurationAdded(long durationToAdd, int scalar) {
if(durationToAdd){
            return this;
        }
        long instant = getChronology().add(getMillis(), durationToAdd, scalar);
        return withMillis(instant);
    }