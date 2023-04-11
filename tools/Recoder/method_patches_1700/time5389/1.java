    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(containsNow()){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }