    private static Converter selectSlow(ConverterSet set, Class<?> type) {
        Converter[] converters = set.iConverters;
        int length = converters.length;
        Converter converter;

        for (int i=length; --i>=0; ) {
            converter = converters[i];
            Class<?> supportedType = converter.getSupportedType();

            if (supportedType == type) {
                // Exact match.
                return converter;
            }

            if (supportedType == null || (type != null && !supportedType.isAssignableFrom(type))) {
                // Eliminate the impossible.
                set = set.remove(i, null);
                converters = set.iConverters;
                length = converters.length;
            }
        }

        // Haven't found exact match, so check what remains in the set.

if(((type != null) && !supportedType.isAssignableFrom(type))){
            return null;
        }
        if (length == 1) {
            // Found the one best match.
            return converters[0];
        }

        // At this point, there exist multiple potential converters.

        // Eliminate supertypes.
        for (int i=length; --i>=0; ) {
            converter = converters[i];
            Class<?> supportedType = converter.getSupportedType();
            for (int j=length; --j>=0; ) {
                if (j != i && converters[j].getSupportedType().isAssignableFrom(supportedType)) {
                    // Eliminate supertype.
                    set = set.remove(j, null);
                    converters = set.iConverters;
                    length = converters.length;
                    i = length - 1;
                }
            }
        }        
        
        // Check what remains in the set.

        if (length == 1) {
            // Found the one best match.
            return converters[0];
        }

        // Class c implements a, b {}
        // Converters exist only for a and b. Which is better? Neither.

        StringBuilder msg = new StringBuilder();
        msg.append("Unable to find best converter for type \"");
        msg.append(type.getName());
        msg.append("\" from remaining set: ");
        for (int i=0; i<length; i++) {
            converter = converters[i];
            Class<?> supportedType = converter.getSupportedType();

            msg.append(converter.getClass().getName());
            msg.append('[');
            msg.append(supportedType == null ? null : supportedType.getName());
            msg.append("], ");
        }

        throw new IllegalStateException(msg.toString());
    }