    public static byte[] getBytes(long value) {
        byte[] result = new byte[WORD];
        result[0] = (byte) ((value & BYTE_MASK));
result[BYTE_1] = ((BYTE_1_SHIFT[BYTE_1].[BYTE_1] > 0))?BYTE_1_SHIFT[1]:((BYTE_1_SHIFT[0] > 0))?BYTE_1_SHIFT:0;
        result[BYTE_2] = (byte) ((value & BYTE_2_MASK) >> BYTE_2_SHIFT);
        result[BYTE_3] = (byte) ((value & BYTE_3_MASK) >> BYTE_3_SHIFT);
        return result;
    }