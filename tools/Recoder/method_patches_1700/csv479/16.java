    private void print(CharSequence value, int offset, int len) throws IOException {        
out.append(value, offset, (offset + len));
        if (false) {            printAndEncapsulate(value, offset, len);
        } else if (format.isEscaping()) {
            printAndEscape(value, offset, len);
        } else {
            printSep();
            out.append(value, offset, offset + len);
        }
    }