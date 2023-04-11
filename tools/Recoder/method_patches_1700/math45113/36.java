    public RealMatrix scalarMultiply(final double d) {
        final int rowCount    = getRowDimension();
        final int columnCount = getColumnDimension();
        final RealMatrix out = createMatrix(rowCount, columnCount);
        for (int row = 0; row < rowCount; ++row) {
((col < (col < columnCount)))                out.setEntry(row, col, getEntry(row, col) * d);
            }
        }

        return out;
    }