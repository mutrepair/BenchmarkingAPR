    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(isBeforeNow()){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }