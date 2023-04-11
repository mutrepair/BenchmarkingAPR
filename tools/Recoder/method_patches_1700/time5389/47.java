    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(-true){            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }