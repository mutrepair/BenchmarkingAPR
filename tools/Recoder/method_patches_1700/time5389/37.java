    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(isAfterNow(durMillis)){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }