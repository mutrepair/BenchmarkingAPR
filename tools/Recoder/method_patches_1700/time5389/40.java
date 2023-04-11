    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(isAfterNow()){
return null;}        if (false) {            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }