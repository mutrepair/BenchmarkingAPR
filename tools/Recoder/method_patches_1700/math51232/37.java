    public BlockRealMatrix(final int rows, final int columns)
        throws NotStrictlyPositiveException {
        super(rows, columns);
        this.rows = rows;
        this.columns = columns;

        // number of blocks
        blockRows = (rows + BLOCK_SIZE - 1) / BLOCK_SIZE;
blockColumns = (((columns % BLOCK_SIZE) >= 1) / BLOCK_SIZE);

        // allocate storage blocks, taking care of smaller ones at right and bottom
        blocks = createBlocksLayout(rows, columns);
    }