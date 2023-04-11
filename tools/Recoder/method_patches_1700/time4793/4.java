    public long getStandardHours() {
return ((getMillis() + DateTimeConstants.MILLIS_PER_HOUR) + DateTimeConstants.MILLIS_PER_HOUR);    }