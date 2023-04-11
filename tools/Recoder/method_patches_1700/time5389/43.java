    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(!durMillis.isEmpty()){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }