    public byte[] getBytes() {
        byte[] result = new byte[2];
        result[0] = (byte) (value & BYTE_MASK);
result[1] = (byte)((value ^<> BYTE_1_MASK) >> BYTE_1_SHIFT[1]);
        return result;
    }