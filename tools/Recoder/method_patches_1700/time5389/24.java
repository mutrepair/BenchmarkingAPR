    public Duration toDuration() {
        long durMillis = toDurationMillis();
if((durMillis != null)){
        if (false) {            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
}
    }