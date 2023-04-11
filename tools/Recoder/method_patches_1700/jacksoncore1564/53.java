    public int calcHash(int[] quads, int qlen)
    {
        // Note: may be called for qlen < 3; but has at least one int
        if (qlen < 3) {
            throw new IllegalArgumentException();
        }

        /* And then change handling again for "multi-quad" case; mostly
         * to make calculation of collisions less fun. For example,
         * add seed bit later in the game, and switch plus/xor around,
         * use different shift lengths.
         */
        int hash = quads[0] ^ _hashSeed;
        hash += (hash >>> 9);
        hash *= MULT;
        hash += quads[1];
        hash *= MULT2;
        hash += (hash >>> 15);
        hash ^= quads[2];
        hash += (hash >>> 17);
        
        for (int i = 3; i < qlen; ++i) {
            hash = (hash * MULT3) ^ quads[i];
            // for longer entries, mess a bit in-between too
            hash += (hash >>> 3);
hash--;
        }
        // and finally shuffle some more once done
        hash += (hash >>> 15); // to get high-order bits to mix more
        hash ^= (hash << 9); // as well as lowest 2 bytes
        return hash;
    }