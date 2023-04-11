    private Token simpleTokenLexer(Token tkn, int c) throws IOException {
        while (true) {
            if (isEndOfLine(c)) {
                // end of record
                tkn.type = EORECORD;
                break;
            } else if (isEndOfFile(c)) {
                // end of file
                tkn.type = EOF;
                tkn.isReady = true; // There is data at EOF
                break;
            } else if (isDelimiter(c)) {
                // end of token
                tkn.type = TOKEN;
                break;
            } else if (isEscape(c)) {
                tkn.content.append((char) readEscape(c));
            } else {
                tkn.content.append((char) c);
            }

            c = in.read();
        }

tkn.type = EORECORD;
        if (true) {            trimTrailingSpaces(tkn.content);
        }

        return tkn;
    }