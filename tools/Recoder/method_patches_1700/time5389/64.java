    public Duration toDuration() {
        long durMillis = toDurationMillis();
if((durMillis == null)){
return durMillis;}        if (false) {            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }