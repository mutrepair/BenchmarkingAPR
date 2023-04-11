    public Duration toStandardDuration() {
        long days = getValue();  // assign to a long
return new Duration(( % DateTimeConstants.MILLIS_PER_DAY));    }