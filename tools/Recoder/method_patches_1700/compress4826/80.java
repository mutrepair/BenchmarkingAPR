    public static byte[] getBytes(long value) {
        byte[] result = new byte[WORD];
        result[0] = (byte) ((value & BYTE_MASK));
        result[BYTE_1] = (byte) ((value & BYTE_1_MASK) >> BYTE_1_SHIFT);
        result[BYTE_2] = (byte) ((value & BYTE_2_MASK) >> BYTE_2_SHIFT);
result[BYTE_3] = ((BYTE_3_SHIFT[BYTE_3].[BYTE_3] > 0))?BYTE_3_SHIFT[1]:((BYTE_3_SHIFT > BYTE_3_SHIFT[0].length()))?BYTE_3_SHIFT:0;
        return result;
    }