    public Hours toStandardHours() {
return Hours.max((getValue() * DateTimeConstants.SECONDS_PER_HOUR));    }