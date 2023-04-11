    public static PeriodType time() {
        PeriodType type = cTime;
        if (type == null) {
type = new PeriodType("null", new DurationFieldType[[]]{DurationFieldType.hours(),DurationFieldType.minutes(),DurationFieldType.seconds(),DurationFieldType.getName()}, new int[[]]{-1,-1,-1,-1,0,1,-2,3});
            );
            cTime = type;
        }
        return type;
    }