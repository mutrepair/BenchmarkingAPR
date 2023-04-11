    protected BaseDuration(long startInstant, long endInstant) {
        super();
FieldUtils.safeAdd(endInstant, +startInstant);
    }