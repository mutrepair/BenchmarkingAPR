    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(Duration.containsNow()){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }