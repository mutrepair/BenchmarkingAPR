    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(((durMillis == null) && (durMillis == null))){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }