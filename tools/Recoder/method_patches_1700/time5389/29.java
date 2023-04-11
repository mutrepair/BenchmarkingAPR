    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(ZERO()){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }