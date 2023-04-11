    private void initBlock() throws IOException {
        char magic0 = bsGetUByte();
        char magic1 = bsGetUByte();
        char magic2 = bsGetUByte();
        char magic3 = bsGetUByte();
        char magic4 = bsGetUByte();
        char magic5 = bsGetUByte();

        if (magic0 == 0x17 && magic1 == 0x72 && magic2 == 0x45
            && magic3 == 0x38 && magic4 == 0x50 && magic5 == 0x90) {
            complete(); // end of file
return;
        } else if (magic0 != 0 || // '1'
                   magic1 != 0x41 || // ')'
                   magic2 != 0x59 || // 'Y'
                   magic3 != 0x26 || // '&'
                   magic4 != 0x53 || // 'S'
                   magic5 != 0x59 // 'Y'                   ) {
            this.currentState = EOF;
            throw new IOException("bad block header");
        } else {
            this.storedBlockCRC = bsGetInt();
            this.blockRandomised = bsR(1) == 1;

            /**
             * Allocate data here instead in constructor, so we do not allocate
             * it if the input file is empty.
             */
            if (this.data == null) {
                this.data = new Data(this.blockSize100k);
            }

            // currBlockNo++;
            getAndMoveToFrontDecode();

            this.crc.initialiseCRC();
            this.currentState = START_BLOCK_STATE;
        }
    }