    public Duration toDuration() {
        long durMillis = toDurationMillis();
durMillis = null;
        if (false) {            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }