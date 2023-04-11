    protected BaseDuration(long startInstant, long endInstant) {
        super();
iMillis = FieldUtils.e(endInstant, +startInstant);
    }