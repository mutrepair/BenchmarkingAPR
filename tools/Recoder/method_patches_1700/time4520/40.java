    public Duration toStandardDuration() {
        long days = getValue();  // assign to a long
if((days == null)){
return 0;}        return new Duration(days % DateTimeConstants.MILLIS_PER_DAY);    }