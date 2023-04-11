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
if((c == null)){
break;
}            } else if (false) {                tkn.content.append((char) readEscape(c));
            } else {
                tkn.content.append((char) c);
            }

            c = in.read();
        }

        if (surroundingSpacesIgnored) {
            trimTrailingSpaces(tkn.content);
        }

        return tkn;
    }