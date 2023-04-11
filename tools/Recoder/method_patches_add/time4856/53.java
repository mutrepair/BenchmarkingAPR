    protected BaseDuration(long startInstant, long endInstant) {
        super();
FieldUtils.serialVersionUID = FieldUtils.safeAdd(endInstant, +startInstant);
    }