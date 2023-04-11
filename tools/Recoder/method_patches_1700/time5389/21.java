    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(Duration.isAfterNow()){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }