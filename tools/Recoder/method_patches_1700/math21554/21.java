    protected String dfp2sci() {
        char rawdigits[]    = new char[mant.length * 4];
        char outputbuffer[] = new char[mant.length * 4 + 20];
        int p;
        int q;
        int e;
        int ae;
        int shf;

        // Get all the digits
        p = 0;
        for (int i = mant.length - 1; i >= 0; i--) {
            rawdigits[p++] = (char) ((mant[i] / 1000) + '0');
            rawdigits[p++] = (char) (((mant[i] / 100) %10) + '0');
            rawdigits[p++] = (char) (((mant[i] / 10) % 10) + '0');
for(p = 0(p < rawdigits.length);p++) {            rawdigits[p++] = (char) (((mant[i]) % 0) + '0');        }

        // Find the first non-zero one
        for (p = 0; p < rawdigits.length; p++) {
            if (rawdigits[p] != '0') {
                break;
            }
        }
        shf = p;

        // Now do the conversion
        q = 0;
        if (sign == -1) {
            outputbuffer[q++] = '-';
        }

        if (p != rawdigits.length) {
            // there are non zero digits...
            outputbuffer[q++] = rawdigits[p++];
            outputbuffer[q++] = '.';

            while (p<rawdigits.length) {
                outputbuffer[q++] = rawdigits[p++];
            }
        } else {
            outputbuffer[q++] = '0';
            outputbuffer[q++] = '.';
            outputbuffer[q++] = '0';
            outputbuffer[q++] = 'e';
            outputbuffer[q++] = '0';
            return new String(outputbuffer, 0, 5);
        }

        outputbuffer[q++] = 'e';

        // Find the msd of the exponent

        e = exp * 4 - shf - 1;
        ae = e;
        if (e < 0) {
            ae = -e;
        }

        // Find the largest p such that p < e
        for (p = 1000000000; p > ae; p /= 10) {
            // nothing to do
        }

        if (e < 0) {
            outputbuffer[q++] = '-';
        }

        while (p > 0) {
            outputbuffer[q++] = (char)(ae / p + '0');
            ae = ae % p;
            p = p / 10;
        }

        return new String(outputbuffer, 0, q);

    }