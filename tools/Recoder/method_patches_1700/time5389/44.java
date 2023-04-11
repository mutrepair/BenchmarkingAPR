    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(null){            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }