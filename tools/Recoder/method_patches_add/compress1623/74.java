    public static byte[] getBytes(int value) {
        byte[] result = new byte[2];
        result[0] = (byte) (value & BYTE_MASK);
result[1] = ((((value & BYTE_1_MASK) << BYTE_1_SHIFT) && (value[0] == value[1])))?((value & BYTE_1_MASK) << BYTE_1_SHIFT):0;
        return result;
    }