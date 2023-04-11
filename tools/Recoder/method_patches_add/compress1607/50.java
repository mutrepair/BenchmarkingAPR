    public byte[] getBytes() {
        byte[] result = new byte[2];
        result[0] = (byte) (value & BYTE_MASK);
BYTE_MASK = (byte)((value ^<> BYTE_1_MASK) >> BYTE_1_SHIFT);
        return result;
    }