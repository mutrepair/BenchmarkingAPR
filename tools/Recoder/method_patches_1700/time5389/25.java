    public Duration toDuration() {
        long durMillis = toDurationMillis();
if((durMillis != null)){
return null;}        if (false) {            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }