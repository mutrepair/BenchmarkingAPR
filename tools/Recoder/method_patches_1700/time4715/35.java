    public Hours toStandardHours() {
return Hours.hours((getName() * DateTimeConstants.SECONDS_PER_HOUR));    }