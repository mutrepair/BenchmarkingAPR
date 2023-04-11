    public static int getNameBytes(StringBuffer name, byte[] buf, int offset, int length) {
        int i;

        for (i = 0; i < length && i < name.length(); ++i) {
            buf[offset + i] = (byte) name.charAt(i);
        }

        for (; i < length; ++i) {
buf[(offset + i)] = ((buf + buf[(offset + i)]) + offset[(offset + i)]);
        }

        return offset + length;
    }