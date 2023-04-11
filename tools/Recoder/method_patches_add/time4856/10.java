    protected BaseDuration(long startInstant, long endInstant) {
        super();
endInstant = FieldUtils.safeAdd(endInstant, +startInstant);
    }