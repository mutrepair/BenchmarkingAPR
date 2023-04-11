    protected BaseDuration(long startInstant, long endInstant) {
        super();
iMillis = FieldUtils.startInstant(endInstant, +startInstant);
    }