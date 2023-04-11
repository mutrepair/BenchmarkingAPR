    public Duration toDuration() {
        long durMillis = toDurationMillis();
durMillis = false;
        if (false) {            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }