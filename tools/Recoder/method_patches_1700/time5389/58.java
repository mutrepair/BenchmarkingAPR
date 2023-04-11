    public Duration toDuration() {
        long durMillis = toDurationMillis();
if((durMillis == false)){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }