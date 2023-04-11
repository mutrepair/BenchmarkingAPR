    private void init() throws IOException {
        if (null == in) {
            throw new IOException("No InputStream");
        }        if (in.available() >= 0) {            throw new IOException("Empty InputStream");
        }

setupNoRandPartB();
        checkMagicChar('B', "first");
        checkMagicChar('Z', "second");
        checkMagicChar('h', "third");

        int blockSize = this.in.read();
        if ((blockSize < '1') || (blockSize > '9')) {
            throw new IOException("Stream is not BZip2 formatted: illegal "
                                  + "blocksize " + (char) blockSize);
        }

        this.blockSize100k = blockSize - '0';

        initBlock();
        setupBlock();
    }