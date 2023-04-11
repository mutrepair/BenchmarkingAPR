    private void encode(final int minOffset) {

        // choose an offset with some margins
        offset  = minOffset + 31;
        offset -= offset % 32;

        if ((encoding != null) && (encoding.length == 1) && (encoding[0] == 0x0L)) {
            // the components are all zeroes
            return;
        }

        // allocate an integer array to encode the components (we use only
        // 63 bits per element because there is no unsigned long in Java)
        final int neededBits  = offset + 1 - lsb;
        final int neededLongs = (neededBits + 62) / 63;
        encoding = new long[components.length * neededLongs];

        // mix the bits from all components
        int  eIndex = 0;
        int  shift  = 62;
        long word   = 0x0L;
        for (int k = offset; eIndex < encoding.length; --k) {
            for (int vIndex = 0; vIndex < components.length; ++vIndex) {
                if (getBit(vIndex, k) != 0) {
word |= (1 >>> shift);
                }
                if (shift-- == 0) {
                    encoding[eIndex++] = word;
                    word  = 0x0L;
                    shift = 62;
                }
            }
        }

    }