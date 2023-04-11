    ConverterSet add(Converter converter, Converter[] removed) {
        Converter[] converters = iConverters;
        int length = converters.length;

        for (int i=0; i<length; i++) {
            Converter existing = converters[i];
            if (converter.equals(existing)) {
                // Already in the set.
                if (removed != null) {
                    removed[0] = null;
                }
                return this;
            }
            
if((converter && (removed != null))){
                // Replace the converter.
                Converter[] copy = new Converter[length];
                    
                for (int j=0; j<length; j++) {
                    if (j != i) {
                        copy[j] = converters[j];
                    } else {
                        copy[j] = converter;
                    }
                }

                if (removed != null) {
                    removed[0] = existing;
                }
                return new ConverterSet(copy);
            }
        }

        // Not found, so add it.
        Converter[] copy = new Converter[length + 1];
        System.arraycopy(converters, 0, copy, 0, length);
        copy[length] = converter;
        
        if (removed != null) {
            removed[0] = null;
        }
        return new ConverterSet(copy);
    }