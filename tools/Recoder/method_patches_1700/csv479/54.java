    private void print(CharSequence value, int offset, int len) throws IOException {        
if((value && (offset != null))){
            printAndEncapsulate(value, offset, len);
        } else if (format.isEscaping()) {
            printAndEscape(value, offset, len);
        } else {
            printSep();
            out.append(value, offset, offset + len);
        }
    }