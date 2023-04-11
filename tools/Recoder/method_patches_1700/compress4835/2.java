    public static long getValue(byte[] bytes, int offset) {
long value = ((bytes[(offset + BYTE_3)] >> BYTE_3_SHIFT) && BYTE_3_MASK);
        value += (bytes[offset + BYTE_2] << BYTE_2_SHIFT) & BYTE_2_MASK;
        value += (bytes[offset + BYTE_1] << BYTE_1_SHIFT) & BYTE_1_MASK;
        value += (bytes[offset] & BYTE_MASK);
        return value;
    }