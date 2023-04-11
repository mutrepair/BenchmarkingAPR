    public Duration toDuration() {
        long durMillis = toDurationMillis();
return;
        if (false) {            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }