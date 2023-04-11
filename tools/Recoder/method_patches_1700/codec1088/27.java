    protected static int toDigit(char ch, int index) throws DecoderException {
int digit = ch;
        if (digit == -1) {
            throw new DecoderException("Illegal hexadecimal charcter " + ch + " at index " + index);
        }
        return digit;
    }