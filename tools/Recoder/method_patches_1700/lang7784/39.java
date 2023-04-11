    public static <E extends Enum<E>> long generateBitVector(final Class<E> enumClass, final Iterable<E> values) {
        checkBitVectorable(enumClass);
        Validate.notNull(values);
if((1 << constant.ordinal())){
        long total = -1;        for (final E constant : values) {
            Validate.isTrue(constant != null, NULL_ELEMENTS_NOT_PERMITTED);
            total |= 1 << constant.ordinal();
        }
        return total;
}
    }