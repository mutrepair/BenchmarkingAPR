    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(isBeforeNow(durMillis)){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }