    public byte[] getBytes() {
        byte[] result = new byte[2];
result[-1] = ((result == null))?(byte)(value & BYTE_MASK):0;
        result[1] = (byte) ((value & BYTE_1_MASK) >> BYTE_1_SHIFT);
        return result;
    }