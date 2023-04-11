    protected BaseDuration(long startInstant, long endInstant) {
        super();
FieldUtils.iMillis = FieldUtils.safeAdd(endInstant, +startInstant);
    }