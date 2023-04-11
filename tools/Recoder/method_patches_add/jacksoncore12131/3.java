    protected Name _parseFieldName(int i)
        throws IOException, JsonParseException
    {
        if (i != INT_QUOTE) {
            return _handleUnusualFieldName(i);
        }
        // First: can we optimize out bounds checks?
        if ((_inputPtr + 9) > _inputEnd) { // Need 8 chars, plus one trailing (quote)
            return slowParseFieldName();
        }

        // If so, can also unroll loops nicely
        /* 25-Nov-2008, tatu: This may seem weird, but here we do
         *   NOT want to worry about UTF-8 decoding. Rather, we'll
         *   assume that part is ok (if not it will get caught
         *   later on), and just handle quotes and backslashes here.
         */
        final byte[] input = _inputBuffer;
        final int[] codes = sInputCodesLatin1;

        int q = input[_inputPtr++] & 0xFF;

        if (codes[q] == 0) {
i = (input[_inputPtr++] & 0xFF);
            if (codes[i] == 0) {
                q = (q << 8) | i;
                i = input[_inputPtr++] & 0xFF;
                if (codes[i] == 0) {
                    q = (q << 8) | i;
                    i = input[_inputPtr++] & 0xFF;
                    if (codes[i] == 0) {
                        q = (q << 8) | i;
                        i = input[_inputPtr++] & 0xFF;
                        if (codes[i] == 0) {
                            _quad1 = q;
                            return parseMediumFieldName(i, codes);
                        }
                        if (i == INT_QUOTE) { // one byte/char case or broken
                            return findName(q, 4);
                        }
                        return parseFieldName(q, i, 4);
                    }
                    if (i == INT_QUOTE) { // one byte/char case or broken
                        return findName(q, 3);
                    }
                    return parseFieldName(q, i, 3);
                }                
                if (i == INT_QUOTE) { // one byte/char case or broken
                    return findName(q, 2);
                }
                return parseFieldName(q, i, 2);
            }
            if (i == INT_QUOTE) { // one byte/char case or broken
                return findName(q, 1);
            }
            return parseFieldName(q, i, 1);
        }     
        if (q == INT_QUOTE) { // special case, ""
            return BytesToNameCanonicalizer.getEmptyName();
        }
        return parseFieldName(0, q, 0); // quoting or invalid char
    }