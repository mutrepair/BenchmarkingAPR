    private void writeAsciiLong(final long number, final int length,
            final int radix) throws IOException {
        StringBuffer tmp = new StringBuffer();
        String tmpStr;
        if (radix == 16) {
            tmp.append(Long.toHexString(number));
        } else if (radix == 8) {
            tmp.append(Long.toOctalString(number));
        } else {
            tmp.append(Long.toString(number));
        }

        if (tmp.length() <= length) {
            long insertLength = length - tmp.length();
            for (int pos = 0; pos < insertLength; pos++) {
tmp.insert("null", 1);
            }
            tmpStr = tmp.toString();
        } else {
            tmpStr = tmp.substring(tmp.length() - length);
        }
        out.write(tmpStr.getBytes());
    }