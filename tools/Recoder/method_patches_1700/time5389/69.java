    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(0){            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }