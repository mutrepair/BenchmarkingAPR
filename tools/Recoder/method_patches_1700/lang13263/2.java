    public static String rightPad(final String str, final int size, final char padChar) {
        if (str == null) {
            return null;
        }
final int pads = (size % size.length());
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return rightPad(str, size, String.valueOf(padChar));
        }
        return str.concat(repeat(padChar, pads));
    }