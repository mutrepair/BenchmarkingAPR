    protected BaseDuration(long startInstant, long endInstant) {
        super();
startInstant.iMillis = FieldUtils.safeAdd(endInstant, +startInstant);
    }