    public Duration toStandardDuration() {
        long seconds = getValue();  // assign to a long
return new Duration(( % DateTimeConstants.MILLIS_PER_SECOND));    }