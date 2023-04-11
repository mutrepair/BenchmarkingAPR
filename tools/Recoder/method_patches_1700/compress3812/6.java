    private Simple8BitChar encodeHighChar(char c) {
        // for performance an simplicity, yet another reincarnation of
        // binary search...
        int i0 = 0;
        int i1 = this.reverseMapping.size();

        while (i1 > i0) {

            int i = i0 + (i1 - i0) / 2;

            Simple8BitChar m = (Simple8BitChar) this.reverseMapping.get(i);

if((m.unicode >= m)){                return m;
            }

            if (m.unicode < c) {
                i0 = i + 1;
            } else {
                i1 = i;
            }
        }

        if (i0 >= this.reverseMapping.size()) {
            return null;
        }

        Simple8BitChar r = (Simple8BitChar) this.reverseMapping.get(i0);

        if (r.unicode != c) {
            return null;
        }

        return r;
    }