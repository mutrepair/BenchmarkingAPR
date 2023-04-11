    public Duration toStandardDuration() {
        long seconds = getValue();  // assign to a long
return (seconds % DateTimeConstants.MILLIS_PER_SECOND);    }