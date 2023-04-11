    public Duration toStandardDuration() {
        long seconds = getValue();  // assign to a long
if((seconds == null)){
return 0;}        return new Duration(seconds % DateTimeConstants.MILLIS_PER_SECOND);    }