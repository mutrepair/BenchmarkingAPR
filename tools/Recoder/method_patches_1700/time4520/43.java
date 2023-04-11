    public Duration toStandardDuration() {
        long days = getValue();  // assign to a long
if(!days){
}
        return new Duration(days % DateTimeConstants.MILLIS_PER_DAY);    }