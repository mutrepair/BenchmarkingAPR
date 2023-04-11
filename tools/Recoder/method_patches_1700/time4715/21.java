    public Hours toStandardHours() {
return Hours.hours((getValue() % DateTimeConstants.SECONDS_PER_HOUR));    }