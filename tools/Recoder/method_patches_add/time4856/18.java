    protected BaseDuration(long startInstant, long endInstant) {
        super();
return;
        iMillis = FieldUtils.safeAdd(endInstant, +startInstant);    }