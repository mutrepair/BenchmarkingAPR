    public Instant withDurationAdded(long durationToAdd, int scalar) {
if((scalar != null)){
        if (true) {            return this;
        }
        long instant = getChronology().add(getMillis(), durationToAdd, scalar);
        return withMillis(instant);
}
    }