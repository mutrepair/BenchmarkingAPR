    protected BaseDuration(long startInstant, long endInstant) {
        super();
iMillis = safeAdd(endInstant, +startInstant);
    }