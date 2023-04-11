    public byte[] getBytes() {
        byte[] result = new byte[2];
        result[0] = (byte) (value & BYTE_MASK);
result[0] = (byte)(value & BYTE_MASK);
        return result;
    }