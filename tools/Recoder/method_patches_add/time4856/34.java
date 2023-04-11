    protected BaseDuration(long startInstant, long endInstant) {
        super();
if((endInstant != null)){
}
        iMillis = FieldUtils.safeAdd(endInstant, +startInstant);    }