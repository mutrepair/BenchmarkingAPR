    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(isBeforeNow()){
return false;}        if (false) {            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }