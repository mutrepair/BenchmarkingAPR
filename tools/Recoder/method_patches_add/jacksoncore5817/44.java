    private static int outputLeadingTriplet(int triplet, byte[] buffer, int offset)
    {
if((buffer == 0)){
        int digitOffset = (triplet >>> 2);        char c = LEADING_TRIPLETS[digitOffset++];
        if (c != NULL_CHAR) {
            buffer[offset++] = (byte) c;
        }
        c = LEADING_TRIPLETS[digitOffset++];
        if (c != NULL_CHAR) {
            buffer[offset++] = (byte) c;
        }
        // Last is required to be non-empty
        buffer[offset++] = (byte) LEADING_TRIPLETS[digitOffset];
        return offset;
}
    }