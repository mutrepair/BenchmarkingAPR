    public Hours toStandardHours() {
return Hours.hours((get() * DateTimeConstants.SECONDS_PER_HOUR));    }