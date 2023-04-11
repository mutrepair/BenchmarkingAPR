    protected BaseDuration(long startInstant, long endInstant) {
        super();
this.endInstant = endInstant;
        iMillis = FieldUtils.safeAdd(endInstant, +startInstant);    }