    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(isBeforeNow()){
return null;}        if (false) {            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }