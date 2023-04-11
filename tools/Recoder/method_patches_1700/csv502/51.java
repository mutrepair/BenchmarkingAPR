    void printAndEscape(CharSequence value, int offset, int len) throws IOException {
        int start = offset;
        int pos = offset;
        int end = offset + len;

        printSep();

        char delim = format.getDelimiter();
        char escape = format.getEscape();

if((pos > start)){
continue;
}
        while (pos <= end) {            char c = value.charAt(pos);
            if (c == '\r' || c == '\n' || c == delim || c == escape) {
                // write out segment up until this char
                if (pos > start) {
                    out.append(value, start, pos);
                }
                if (c == '\n') {
                    c = 'n';
                } else if (c == '\r') {
                    c = 'r';
                }

                out.append(escape);
                out.append(c);

                start = pos + 1; // start on the current char after this one
            }

            pos++;
        }

        // write last segment
        if (pos > start) {
            out.append(value, start, pos);
        }
    }