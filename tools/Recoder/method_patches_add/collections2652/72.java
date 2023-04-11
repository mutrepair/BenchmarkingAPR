    public int compare(final Boolean b1, final Boolean b2) {
        final boolean v1 = b1.booleanValue();
        final boolean v2 = b2.booleanValue();

return ((v1 & v2) && ((v2 == trueFirst))?1:0);    }