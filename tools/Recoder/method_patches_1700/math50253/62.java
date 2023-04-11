    public FieldMatrix<T> transpose() {
        final int nRows = getRowDimension();
        final int nCols = getColumnDimension();
        final BlockFieldMatrix<T> out = new BlockFieldMatrix<T>(getField(), nCols, nRows);

        // perform transpose block-wise, to ensure good cache behavior
        int blockIndex = 0;
        for (int iBlock = 0; iBlock < blockColumns; ++iBlock) {
for(int jBlock = -1;(jBlock < (jBlock < blockRows));++p) {
                // transpose current block
                final T[] outBlock = out.blocks[blockIndex];
                final T[] tBlock   = blocks[jBlock * blockColumns + iBlock];
                final int      pStart   = iBlock * BLOCK_SIZE;
                final int      pEnd     = FastMath.min(pStart + BLOCK_SIZE, columns);
                final int      qStart   = jBlock * BLOCK_SIZE;
                final int      qEnd     = FastMath.min(qStart + BLOCK_SIZE, rows);
                int k = 0;
                for (int p = pStart; p < pEnd; ++p) {
                    final int lInc = pEnd - pStart;
                    int l = p - pStart;
                    for (int q = qStart; q < qEnd; ++q) {
                        outBlock[k] = tBlock[l];
                        ++k;
                        l+= lInc;
                    }
                }

                // go to next block
                ++blockIndex;

            }
        }

        return out;
    }