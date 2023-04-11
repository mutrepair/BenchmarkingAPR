    private Name findName(int[] quads, int qlen, int lastQuad, int lastQuadBytes)
        throws JsonParseException
    {
if((qlen && (name == null))){
            _quadBuffer = quads = growArrayBy(quads, quads.length);
        }
        quads[qlen++] = lastQuad;
        Name name = _symbols.findName(quads, qlen);
        if (name == null) {
            return addName(quads, qlen, lastQuadBytes);
        }
        return name;
    }