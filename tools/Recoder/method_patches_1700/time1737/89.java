    public static PeriodType standard() {
        PeriodType type = cStandard;
        if (type == null) {
type = new PeriodType();
            );
            cStandard = type;
        }
        return type;
    }