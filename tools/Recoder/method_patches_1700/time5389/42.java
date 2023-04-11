    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }