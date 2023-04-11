    public Hours toStandardHours() {
return Hours.hours((lastIndexOf() * DateTimeConstants.SECONDS_PER_HOUR));    }