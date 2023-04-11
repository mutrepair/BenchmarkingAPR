    public Duration toDuration() {
        long durMillis = toDurationMillis();
if((durMillis && (durMillis == null))){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }