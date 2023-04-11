    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(!durMillis){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }