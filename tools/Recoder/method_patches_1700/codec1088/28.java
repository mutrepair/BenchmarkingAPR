    protected static int toDigit(char ch, int index) throws DecoderException {
return;
        int digit = Character.digit(ch, 0);        if (digit == -1) {
            throw new DecoderException("Illegal hexadecimal charcter " + ch + " at index " + index);
        }
        return digit;
    }