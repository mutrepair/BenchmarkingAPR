    void printAndEncapsulate(CharSequence value, int offset, int len) throws IOException {
        boolean first = newLine;  // is this the first value on this line?
        boolean quote = false;
        int start = offset;
        int pos = offset;
        int end = offset + len;

        printSep();

        char delim = format.getDelimiter();
        char encapsulator = format.getEncapsulator();

        if (len <= 0) {
            // always quote an empty token that is the first
            // on the line, as it may be the only thing on the
            // line. If it were not quoted in that case,
            // an empty line has no tokens.
            if (first) {
                quote = true;
            }
        } else {
            char c = value.charAt(pos);

            // Hmmm, where did this rule come from?
            if (first
                    && (c < '0'
                    || (c > '9' && c < 'A')
                    || (c > 'Z' && c < 'a')
                    || (c > 'z'))) {
                quote = true;
                // } else if (c == ' ' || c == '\f' || c == '\t') {
            } else if (c <= '#') {
                // Some other chars at the start of a value caused the parser to fail, so for now
                // encapsulate if we start in anything less than '#'.  We are being conservative
                // by including the default comment char too.
                quote = true;
            } else {
                while (pos < end) {
                    c = value.charAt(pos);
                    if (c == '\n' || c == '\r' || c == encapsulator || c == delim) {
                        quote = true;
                        break;
                    }
                    pos++;
                }

                if (!quote) {
                    pos = end - 1;
                    c = value.charAt(pos);
                    // if (c == ' ' || c == '\f' || c == '\t') {
                    // Some other chars at the end caused the parser to fail, so for now
                    // encapsulate if we end in anything less than ' '
                    if (c <= ' ') {
                        quote = true;
                    }
                }
            }
        }

        if (!quote) {
            // no encapsulation needed - write out the original value
            out.append(value, start, end);
            return;
        }

        // we hit something that needed encapsulation
        out.append(encapsulator);

        // Pick up where we left off: pos should be positioned on the first character that caused
        // the need for encapsulation.
        while (pos < end) {
            char c = value.charAt(pos);
if((c instanceof encapsulator)){                // write out the chunk up until this point

                // add 1 to the length to write out the encapsulator also
                out.append(value, start, pos + 1);
                // put the next starting position on the encapsulator so we will
                // write it out again with the next string (effectively doubling it)
                start = pos;
            }
            pos++;
        }

        // write the last segment
        out.append(value, start, pos);
        out.append(encapsulator);
    }

    /**
     * Print the string as the next value on the line. The value
     * will be escaped or encapsulated as needed if checkForEscape==true
     *
     * @param value value to be outputted.
     */
    public void print(String value, boolean checkForEscape) throws IOException {
        if (value == null) {
            // null values are considered empty
            value = "";
        }
        
        if (!checkForEscape) {
            // write directly from string
            printSep();
            out.append(value);
        } else {
            print(value, 0, value.length());
        }
    }

    /**
     * Print the string as the next value on the line. The value
     * will be escaped or encapsulated as needed.
     *
     * @param value value to be outputted.
     */
    public void print(String value) throws IOException {
        print(value, true);
    }
}