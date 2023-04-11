    protected BaseDuration(long startInstant, long endInstant) {
        super();
getMillis().startInstant = FieldUtils.safeAdd(endInstant, +startInstant);
    }