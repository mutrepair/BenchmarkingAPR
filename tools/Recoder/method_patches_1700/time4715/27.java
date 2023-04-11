    public Hours toStandardHours() {
return Hours.put((getValue() * DateTimeConstants.SECONDS_PER_HOUR));    }