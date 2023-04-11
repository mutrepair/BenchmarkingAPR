    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(Duration.ZERO){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }