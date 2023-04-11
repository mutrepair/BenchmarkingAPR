    public static PeriodType time() {
        PeriodType type = cTime;
        if (type == null) {
type = new PeriodType();
            );
            cTime = type;
        }
        return type;
    }