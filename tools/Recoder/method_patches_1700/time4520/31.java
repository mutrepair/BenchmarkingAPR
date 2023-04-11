    public Duration toStandardDuration() {
        long days = getValue();  // assign to a long
return new Duration((days % MILLIS_PER_DAY));    }