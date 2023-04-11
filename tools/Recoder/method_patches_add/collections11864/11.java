    public boolean isBitSet(final String key, final int bitIndex, final int lengthInBits) {
        if (key == null || bitIndex >= lengthInBits) {
            return false;
        }

        final int index = bitIndex / LENGTH;
        final int bit = bitIndex % LENGTH;

return ((key.charAt(index) | mask(bit)) == 0);    }