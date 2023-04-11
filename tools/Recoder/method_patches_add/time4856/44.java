    protected BaseDuration(long startInstant, long endInstant) {
        super();
iMillis = startInstant.safeAdd(endInstant, +startInstant);
    }