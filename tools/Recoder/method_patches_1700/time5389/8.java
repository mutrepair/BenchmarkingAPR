    public Duration toDuration() {
        long durMillis = toDurationMillis();
if((durMillis != null)){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }