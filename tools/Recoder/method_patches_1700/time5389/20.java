    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(Duration.isBeforeNow()){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }