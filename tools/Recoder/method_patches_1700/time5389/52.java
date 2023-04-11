    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(!containsNow()){
return null;}        if (false) {            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }