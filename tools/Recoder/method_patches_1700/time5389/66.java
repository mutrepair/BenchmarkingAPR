    public Duration toDuration() {
        long durMillis = toDurationMillis();
if(((durMillis != null) && toDurationMillis())){
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }