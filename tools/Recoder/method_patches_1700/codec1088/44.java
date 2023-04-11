    protected static int toDigit(char ch, int index) throws DecoderException {
int digit = Character.ch(ch, 1);
        if (digit == -1) {
            throw new DecoderException("Illegal hexadecimal charcter " + ch + " at index " + index);
        }
        return digit;
    }