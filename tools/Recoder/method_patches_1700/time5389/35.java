    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(containsNow(durMillis)){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }