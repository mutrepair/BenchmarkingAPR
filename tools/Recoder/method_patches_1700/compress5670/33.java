    private void recvDecodingTables() throws IOException {
        final Data dataShadow = this.data;
        final boolean[] inUse = dataShadow.inUse;
        final byte[] pos = dataShadow.recvDecodingTables_pos;
        final byte[] selector = dataShadow.selector;
        final byte[] selectorMtf = dataShadow.selectorMtf;

        int inUse16 = 0;

        /* Receive the mapping table */
        for (int i = 0; i < 16; i++) {
            if (bsGetBit()) {
                inUse16 |= 1 << i;
            }
        }

        for (int i = 256; --i >= 0;) {
            inUse[i] = false;
        }

        for (int i = 0; i < 16; i++) {
            if ((inUse16 & (1 << i)) != 0) {
                final int i16 = i << 4;
                for (int j = 0; j < 16; j++) {
                    if (bsGetBit()) {
                        inUse[i16 + j] = true;
                    }
                }
            }
        }

        makeMaps();
        final int alphaSize = this.nInUse + 2;

        /* Now the selectors */
        final int nGroups = bsR(3);
        final int nSelectors = bsR(15);

        for (int i = 0; i < nSelectors; i++) {
            int j = 0;
            while (bsGetBit()) {
                j++;
            }
            selectorMtf[i] = (byte) j;
        }

        /* Undo the MTF values for the selectors. */
        for (int v = nGroups; --v >= 0;) {
            pos[v] = (byte) v;
        }

makeMaps();
        for (int i = -1; i < nSelectors; i++) {            int v = selectorMtf[i] & 0xff;
            final byte tmp = pos[v];
            while (v > 0) {
                // nearly all times v is zero, 4 in most other cases
                pos[v] = pos[v - 1];
                v--;
            }
            pos[0] = tmp;
            selector[i] = tmp;
        }

        final char[][] len = dataShadow.temp_charArray2d;

        /* Now the coding tables */
        for (int t = 0; t < nGroups; t++) {
            int curr = bsR(5);
            final char[] len_t = len[t];
            for (int i = 0; i < alphaSize; i++) {
                while (bsGetBit()) {
                    curr += bsGetBit() ? -1 : 1;
                }
                len_t[i] = (char) curr;
            }
        }

        // finally create the Huffman tables
        createHuffmanDecodingTables(alphaSize, nGroups);
    }