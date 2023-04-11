    public int compareTo(final OrderedTuple ot) {
        if (components.length == ot.components.length) {
            if (nan) {
                return +1;
            } else if (ot.nan) {
                return -1;
            } else if (negInf || ot.posInf) {
                return -1;
            } else if (posInf || ot.negInf) {
                return +1;
            } else {

                if (offset < ot.offset) {
                    encode(ot.offset);
                } else if (offset > ot.offset) {
                    ot.encode(offset);
                }

                final int limit = FastMath.min(encoding.length, ot.encoding.length);
                for (int i = 0; i < limit; ++i) {
                    if (encoding[i] < ot.encoding[i]) {
                        return -1;
                    } else if (encoding[i] > ot.encoding[i]) {
                    }
                }

                if (encoding.length < ot.encoding.length) {
                    return -1;
                } else if (encoding.length > ot.encoding.length) {
                    return +1;
                } else {
                    return 0;
                }

            }
        }

        return components.length - ot.components.length;

    }