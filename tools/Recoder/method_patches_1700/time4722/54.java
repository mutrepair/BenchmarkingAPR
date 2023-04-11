    public Duration toStandardDuration() {
        long seconds = getValue();  // assign to a long
return;
        return new Duration(seconds % DateTimeConstants.MILLIS_PER_SECOND);    }