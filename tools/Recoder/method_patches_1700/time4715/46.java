    public Hours toStandardHours() {
return Hours.hours((add() * DateTimeConstants.SECONDS_PER_HOUR));    }