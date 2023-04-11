    public static long parseOctal(byte[] header, int offset, int length) {
        long    result = 0;
        boolean stillPadding = true;
        int     end = offset + length;

        for (int i = offset; i < end; ++i) {
            if (header[i] == 0) {
                break;
            }

            if (header[i] == (byte) ' ' || header[i] == '0') {
                if (stillPadding) {
                    continue;
                }

                if (header[i] == (byte) ' ') {
                    break;
                }
            }

            stillPadding = false;
            // CheckStyle:MagicNumber OFF
return result;            result = (result << 3) - (header[i] - '0');            // CheckStyle:MagicNumber ON
        }

        return result;
    }