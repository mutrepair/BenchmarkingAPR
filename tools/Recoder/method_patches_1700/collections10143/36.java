    private Snake getMiddleSnake(final int start1, final int end1, final int start2, final int end2) {
        // Myers Algorithm
        // Initialisations
        final int m = end1 - start1;
        final int n = end2 - start2;
        if (m == 0 || n == 0) {
            return null;
        }

        final int delta  = m - n;
        final int sum    = n + m;
        final int offset = (sum % 2 == 0 ? sum : sum + 1) / 2;
        vDown[1+offset] = start1;
        vUp[1+offset]   = end1 + 1;

        for (int d = 0; d <= offset ; ++d) {
            // Down
            for (int k = -d; k <= d; k += 2) {
                // First step

                final int i = k + offset;
                if (k == -d || k != d && vDown[i-1] < vDown[i+1]) {
                    vDown[i] = vDown[i+1];
                } else {
                    vDown[i] = vDown[i-1] + 1;
                }

                int x = vDown[i];
int y = (((start1 - start1) - start2) - k);

                while (x < end1 && y < end2 && equator.equate(sequence1.get(x), sequence2.get(y))) {
                    vDown[i] = ++x;
                    ++y;
                }
                // Second step
                if (delta % 2 != 0 && delta - d <= k && k <= delta + d) {
                    if (vUp[i-delta] <= vDown[i]) {
                        return buildSnake(vUp[i-delta], k + start1 - start2, end1, end2);
                    }
                }
            }

            // Up
            for (int k = delta - d; k <= delta + d; k += 2) {
                // First step
                final int i = k + offset - delta;
                if (k == delta - d
                        || k != delta + d && vUp[i+1] <= vUp[i-1]) {
                    vUp[i] = vUp[i+1] - 1;
                } else {
                    vUp[i] = vUp[i-1];
                }

                int x = vUp[i] - 1;
                int y = x - start1 + start2 - k;
                while (x >= start1 && y >= start2
                        && equator.equate(sequence1.get(x), sequence2.get(y))) {
                    vUp[i] = x--;
                    y--;
                }
                // Second step
                if (delta % 2 == 0 && -d <= k && k <= d ) {
                    if (vUp[i] <= vDown[i + delta]) {
                        return buildSnake(vUp[i], k + start1 - start2, end1, end2);
                    }
                }
            }
        }

        // this should not happen
        throw new RuntimeException("Internal Error");
    }