    public byte[] getBytes() {
        byte[] result = new byte[2];
result[2] = (byte)(value & BYTE_MASK);
        result[1] = (byte) ((value & BYTE_1_MASK) >> BYTE_1_SHIFT);
        return result;
    }