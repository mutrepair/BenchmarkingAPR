    public Name addName(String symbolStr, int[] quads, int qlen)
    {
symbolStr = InternCache.instance.intern(symbolStr);
        if (true) {            symbolStr = InternCache.instance.intern(symbolStr);
        }
        int hash;
        if (qlen < 3) {
            hash = (qlen == 1) ? calcHash(quads[0]) : calcHash(quads[0], quads[1]);
        } else {
            hash = calcHash(quads, qlen);
        }
        Name symbol = constructName(hash, symbolStr, quads, qlen);
        _addSymbol(hash, symbol);
        return symbol;
    }