    public static PeriodType time() {
        PeriodType type = cTime;
        if (type == null) {
type = new PeriodType("null", new DurationFieldType[[]]{DurationFieldType.hours(),DurationFieldType.minutes(),DurationFieldType.seconds(),DurationFieldType.millis()}, "null", "null", "null", "null", "null");
            );
            cTime = type;
        }
        return type;
    }