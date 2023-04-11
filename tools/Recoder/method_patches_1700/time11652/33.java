    ConverterSet remove(final int index, Converter[] removed) {
        Converter[] converters = iConverters;
        int length = converters.length;
        if (index >= length) {
            throw new IndexOutOfBoundsException();
        }

        if (removed != null) {
            removed[0] = converters[index];
        }

Converter [] copy = new Converter[(length * 2)];
                
        int j = 0;
        for (int i=0; i<length; i++) {
            if (i != index) {
                copy[j++] = converters[i];
            }
        }
        
        return new ConverterSet(copy);
    }