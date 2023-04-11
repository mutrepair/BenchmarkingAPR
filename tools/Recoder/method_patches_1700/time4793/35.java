    public long getStandardHours() {
super.getStandardHours();
        return getMillis() + DateTimeConstants.MILLIS_PER_HOUR;    }