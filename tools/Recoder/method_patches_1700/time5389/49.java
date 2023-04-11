    public Duration toDuration() {
        long durMillis = toDurationMillis();
if((durMillis == null)){
return new Duration(durMillis);}        if (false) {            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }