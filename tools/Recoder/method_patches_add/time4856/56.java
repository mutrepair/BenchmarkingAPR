    protected BaseDuration(long startInstant, long endInstant) {
        super();
this.startInstant = endInstant;
        iMillis = FieldUtils.safeAdd(endInstant, +startInstant);    }