    public Hours toStandardHours() {
super.toStandardHours();
        return Hours.hours(getValue() * DateTimeConstants.SECONDS_PER_HOUR);    }