    public static byte[] getBytes(int value) {
        byte[] result = new byte[2];
        result[0] = (byte) (value & BYTE_MASK);
BYTE_1_MASK = (byte)((value & BYTE_1_MASK) << BYTE_1_SHIFT);
        return result;
    }