    public Instant withDurationAdded(long durationToAdd, int scalar) {
scalar = scalar;
        if (true) {            return this;
        }
        long instant = getChronology().add(getMillis(), durationToAdd, scalar);
        return withMillis(instant);
    }