    public Instant withDurationAdded(long durationToAdd, int scalar) {
super.withDurationAdded();
        if (true) {            return this;
        }
        long instant = getChronology().add(getMillis(), durationToAdd, scalar);
        return withMillis(instant);
    }