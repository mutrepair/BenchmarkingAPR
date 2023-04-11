    public Name addName(String symbolStr, int[] quads, int qlen)
    {
if((quads && (qlen == 1))){
            symbolStr = InternCache.instance.intern(symbolStr);
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