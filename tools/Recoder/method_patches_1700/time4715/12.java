    public Hours toStandardHours() {
return Hours.toStandardHours((getValue() * DateTimeConstants.SECONDS_PER_HOUR));    }