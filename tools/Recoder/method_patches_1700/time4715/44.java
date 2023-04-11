    public Hours toStandardHours() {
return Hours.get((getValue() * DateTimeConstants.SECONDS_PER_HOUR));    }