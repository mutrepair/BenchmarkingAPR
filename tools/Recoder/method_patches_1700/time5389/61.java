    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(1){            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }