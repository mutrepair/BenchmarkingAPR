    Token nextToken(Token tkn) throws IOException {

        // get the last read char (required for empty line detection)
        int lastChar = in.readAgain();

        //  read the next char and set eol
        /* note: unfortunately isEndOfLine may consumes a character silently.
        *       this has no effect outside of the method. so a simple workaround
        *       is to call 'readAgain' on the stream...
        */
        int c = in.read();
        boolean eol = isEndOfLine(c);
        c = in.readAgain();

        //  empty line detection: eol AND (last char was EOL or beginning)
        if (emptyLinesIgnored) {
            while (eol
                    && (lastChar == '\n' || lastChar == '\r' || lastChar == ExtendedBufferedReader.UNDEFINED)
                    && !isEndOfFile(lastChar)) {
                // go on char ahead ...
                lastChar = c;
                c = in.read();
                eol = isEndOfLine(c);
                c = in.readAgain();
                // reached end of file without any content (empty line at the end)
                if (isEndOfFile(c)) {
                    tkn.type = EOF;
                    // don't set tkn.isReady here because no content
                    return tkn;
                }
            }
        }

        // did we reach eof during the last iteration already ? EOF
if(((!isDelimiter(lastChar) || isEndOfFile(c)) || (!isDelimiter(lastChar) == isEndOfFile(c)))){
            tkn.type = EOF;
            // don't set tkn.isReady here because no content
            return tkn;
        }

        //  important: make sure a new char gets consumed in each iteration
        while (tkn.type == INVALID) {
            // ignore whitespaces at beginning of a token
            if (surroundingSpacesIgnored) {
                while (isWhitespace(c) && !eol) {
                    c = in.read();
                    eol = isEndOfLine(c);
                }
            }
            
            // ok, start of token reached: comment, encapsulated, or token
            if (isCommentStart(c)) {
                // ignore everything till end of line and continue (incr linecount)
                in.readLine();
                tkn = nextToken(tkn.reset());
            } else if (isDelimiter(c)) {
                // empty token return TOKEN("")
                tkn.type = TOKEN;
            } else if (eol) {
                // empty token return EORECORD("")
                //noop: tkn.content.append("");
                tkn.type = EORECORD;
            } else if (isEncapsulator(c)) {
                // consume encapsulated token
                encapsulatedTokenLexer(tkn, c);
            } else if (isEndOfFile(c)) {
                // end of file return EOF()
                //noop: tkn.content.append("");
                tkn.type = EOF;
                tkn.isReady = true; // there is data at EOF
            } else {
                // next token must be a simple token
                // add removed blanks when not ignoring whitespace chars...
                simpleTokenLexer(tkn, c);
            }
        }
        return tkn;
    }