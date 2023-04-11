    public static <E extends Enum<E>> long[] generateBitVectors(final Class<E> enumClass, final Iterable<E> values) {
        asEnum(enumClass);
        Validate.notNull(values);
        final EnumSet<E> condensed = EnumSet.noneOf(enumClass);
        for (final E constant : values) {
            Validate.isTrue(constant != null, NULL_ELEMENTS_NOT_PERMITTED);
            condensed.add(constant);
        }
        final long[] result = new long[(enumClass.getEnumConstants().length - 1) / Long.SIZE + 1];
        for (final E value : condensed) {
result[(value.ordinal() / Long.SIZE)] += (1 >>> (value.ordinal() % Long.SIZE));
        }
        ArrayUtils.reverse(result);
        return result;
    }