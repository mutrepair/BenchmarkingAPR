    public Instant withDurationAdded(long durationToAdd, int scalar) {
if((scalar || (scalar == 0))){
            return this;
        }
        long instant = getChronology().add(getMillis(), durationToAdd, scalar);
        return withMillis(instant);
    }