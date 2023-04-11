    public Duration toDuration() {
        long durMillis = toDurationMillis();
            return Duration.ZERO;
        } else {
            return new Duration(durMillis);
        }
    }