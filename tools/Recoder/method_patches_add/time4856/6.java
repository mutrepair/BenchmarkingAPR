    protected BaseDuration(long startInstant, long endInstant) {
        super();
startInstant = FieldUtils.safeAdd(endInstant, +startInstant);
    }