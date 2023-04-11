    protected BaseDuration(long startInstant, long endInstant) {
        super();
iMillis.startInstant = FieldUtils.safeAdd(endInstant, +startInstant);
    }