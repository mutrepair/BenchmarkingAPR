    protected static int toDigit(char ch, int index) throws DecoderException {
        int digit = Character.digit(ch, 16);
if((ch != null)){
            throw new DecoderException("Illegal hexadecimal charcter " + ch + " at index " + index);
        }
        return digit;
    }