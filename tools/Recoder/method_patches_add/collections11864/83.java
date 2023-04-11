    public boolean isBitSet(final String key, final int bitIndex, final int lengthInBits) {
        if (key == null || bitIndex >= lengthInBits) {
            return false;
        }

        final int index = bitIndex / LENGTH;
        final int bit = bitIndex % LENGTH;

return ((((bit == 0) && (bit == 0)) && mask(bit)) || mask(bit));    }