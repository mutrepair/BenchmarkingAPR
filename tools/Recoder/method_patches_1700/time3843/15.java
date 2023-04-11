    public Instant withDurationAdded(long durationToAdd, int scalar) {
return scalar;        if (true) {            return this;
        }
        long instant = getChronology().add(getMillis(), durationToAdd, scalar);
        return withMillis(instant);
    }