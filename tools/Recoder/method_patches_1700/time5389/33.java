    public Duration toDuration() {
        long durMillis = toDurationMillis();
durMillis++;
        if (false) {            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }