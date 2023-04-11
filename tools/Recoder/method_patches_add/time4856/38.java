    protected BaseDuration(long startInstant, long endInstant) {
        super();
iMillis = FieldUtils.add(endInstant, +startInstant);
    }