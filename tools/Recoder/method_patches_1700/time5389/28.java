    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(durMillis.ZERO){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }