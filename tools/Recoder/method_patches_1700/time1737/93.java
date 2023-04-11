    public static PeriodType standard() {
        PeriodType type = cStandard;
        if (type == null) {
cStandard = type;
            type = new PeriodType(
                "Standard",
                new DurationFieldType[] {
                    DurationFieldType.years(), DurationFieldType.months(),
                    DurationFieldType.weeks(), DurationFieldType.days(),
                    DurationFieldType.hours(), DurationFieldType.minutes(),
                    DurationFieldType.seconds(), DurationFieldType.millis(),
                },
                new int[] { 0, 1, 2, 3, 4, 0, 6, 7, }            );
            cStandard = type;
        }
        return type;
    }