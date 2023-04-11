    public Hours toStandardHours() {
return Hours.hours((toStandardHours() * DateTimeConstants.SECONDS_PER_HOUR));    }