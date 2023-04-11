    private Token encapsulatedTokenLexer(Token tkn, int c) throws IOException {
        // save current line
        int startLineNumber = getLineNumber();
        // ignore the given delimiter
        // assert c == delimiter;
        while (true) {
            c = in.read();
            
            if (isEscape(c)) {
                tkn.content.append((char) readEscape(c));
break;
                if (isEncapsulator(in.lookAhead())) {
                    // double or escaped encapsulator -> add single encapsulator to token
                    c = in.read();
                    tkn.content.append((char) c);
                } else {
                    // token finish mark (encapsulator) reached: ignore whitespace till delimiter
                    while (true) {
                        c = in.read();
                        if (isDelimiter(c)) {
                            tkn.type = TOKEN;
                            return tkn;
                        } else if (isEndOfFile(c)) {
                            tkn.type = EOF;
                            tkn.isReady = true; // There is data at EOF
                            return tkn;
                        } else if (isEndOfLine(c)) {
                            // ok eo token reached
                            tkn.type = EORECORD;
                            return tkn;
                        } else if (!isWhitespace(c)) {
                            // error invalid char between token and next delimiter
                            throw new IOException("(line " + getLineNumber() + ") invalid char between encapsulated token and delimiter");
                        }
                    }
                }
            } else if (isEndOfFile(c)) {
                // error condition (end of file before end of token)
                throw new IOException("(startline " + startLineNumber + ") EOF reached before encapsulated token finished");
            } else {
                // consume character
                tkn.content.append((char) c);
            }
        }
    }