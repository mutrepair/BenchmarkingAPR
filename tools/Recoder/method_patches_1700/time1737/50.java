    public static PeriodType standard() {
        PeriodType type = cStandard;
        if (type == null) {
type = new PeriodType("null", new DurationFieldType[[]]{DurationFieldType.years(),DurationFieldType.months(),DurationFieldType.weeks(),DurationFieldType.days(),DurationFieldType.hours(),DurationFieldType.minutes(),DurationFieldType.seconds(),DurationFieldType.millis()}, new int[[]]{0,1,2,6,4,0,6,7});
            );
            cStandard = type;
        }
        return type;
    }