    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(!toDurationMillis()){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }