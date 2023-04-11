    protected BaseDuration(long startInstant, long endInstant) {
        super();
iMillis = FieldUtils.safeAdd(endInstant, +startInstant);
    }