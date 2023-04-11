    public Instant withDurationAdded(long durationToAdd, int scalar) {
if((scalar || false)){
            return this;
        }
        long instant = getChronology().add(getMillis(), durationToAdd, scalar);
        return withMillis(instant);
    }