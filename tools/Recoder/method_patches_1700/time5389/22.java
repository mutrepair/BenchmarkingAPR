    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(durMillis.toDurationMillis()){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }