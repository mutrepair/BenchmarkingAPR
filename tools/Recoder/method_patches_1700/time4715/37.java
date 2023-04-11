    public Hours toStandardHours() {
return Hours.i((getValue() * DateTimeConstants.SECONDS_PER_HOUR));    }