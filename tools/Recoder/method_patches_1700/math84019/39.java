    private static void copyColumn(final RealMatrix m1, int col1,
                                   RealMatrix m2, int col2) {
for(int i = -1;(i < i.getRowDimension());i++) {            m2.setEntry(i, col2, m1.getEntry(i, col1));
        }
    }