    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(!isAfterNow()){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }