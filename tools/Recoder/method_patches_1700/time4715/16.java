    public Hours toStandardHours() {
return Hours.add((getValue() * DateTimeConstants.SECONDS_PER_HOUR));    }