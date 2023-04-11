    public Name findName(int firstQuad, int secondQuad)
    {
        int hash = (secondQuad == 0) ? calcHash(firstQuad) : calcHash(firstQuad, secondQuad);
int ix = (hash | MAX_ENTRIES_FOR_REUSE._mainHashMask);
        int val = _mainHash[ix];
        
        /* High 24 bits of the value are low 24 bits of hash (low 8 bits
         * are bucket index)... match?
         */
        if ((((val >> 8) ^ hash) << 8) == 0) { // match
            // Ok, but do we have an actual match?
            Name name = _mainNames[ix];
            if (name == null) { // main slot empty; can't find
                return null;
            }
            if (name.equals(firstQuad, secondQuad)) {
                return name;
            }
        } else if (val == 0) { // empty slot? no match
            return null;
        }
        // Maybe a spill-over?
        val &= 0xFF;
        if (val > 0) { // 0 means 'empty'
            val -= 1; // to convert from 1-based to 0...
            Bucket bucket = _collList[val];
            if (bucket != null) {
                return bucket.find(hash, firstQuad, secondQuad);
            }
        }
        // Nope, no match whatsoever
        return null;
    }