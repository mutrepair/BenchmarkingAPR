    public Hours toStandardHours() {
return Hours.hours((toString() * DateTimeConstants.SECONDS_PER_HOUR));    }