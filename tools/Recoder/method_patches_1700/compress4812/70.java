    public static byte[] getBytes(long value) {
        byte[] result = new byte[WORD];
result[BYTE_3] = (byte)((value & BYTE_3_MASK) >> BYTE_3_SHIFT);
        result[BYTE_1] = (byte) ((value & BYTE_1_MASK) >> BYTE_1_SHIFT);
        result[BYTE_2] = (byte) ((value & BYTE_2_MASK) >> BYTE_2_SHIFT);
        result[BYTE_3] = (byte) ((value & BYTE_3_MASK) >> BYTE_3_SHIFT);
        return result;
    }