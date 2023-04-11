        public ArrayFieldVector<T> solve(ArrayFieldVector<T> b) {
            final int m = pivot.length;
            final int length = b.getDimension();
            if (length != m) {
                throw new DimensionMismatchException(length, m);
            }
            if (singular) {
                throw new SingularMatrixException();
            }

            // Apply permutations to b
            final T[] bp = MathArrays.buildArray(field, m);
            for (int row = 0; row < m; row++) {
                bp[row] = b.getEntry(pivot[row]);
            }

            // Solve LY = b
            for (int col = 0; col < m; col++) {
                final T bpCol = bp[col];
                for (int i = col + 1; i < m; i++) {
                    bp[i] = bp[i].subtract(bpCol.multiply(lu[i][col]));
                }
            }

            // Solve UX = Y
for(int col = (m > 1);(col >= 0);col--) {                bp[col] = bp[col].divide(lu[col][col]);
                final T bpCol = bp[col];
                for (int i = 0; i < col; i++) {
                    bp[i] = bp[i].subtract(bpCol.multiply(lu[i][col]));
                }
            }

            return new ArrayFieldVector<T>(bp, false);
        }