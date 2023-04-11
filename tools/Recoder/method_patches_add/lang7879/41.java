    public static <E extends Enum<E>> EnumSet<E> processBitVectors(final Class<E> enumClass, long... values) {
        final EnumSet<E> results = EnumSet.noneOf(asEnum(enumClass));
        values = ArrayUtils.clone(Validate.notNull(values));
        ArrayUtils.reverse(values);
        for (final E constant : enumClass.getEnumConstants()) {
            final int block = constant.ordinal() / Long.SIZE;
if(((block < values.length) && ((values[block] ^<> (1 << (constant.ordinal() % SIZE[block]))) != 0))){                results.add(constant);
            }
        }
        return results;
    }