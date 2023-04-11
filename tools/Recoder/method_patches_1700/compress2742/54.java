    public byte[] readRecord() throws IOException {
        if (debug) {
            System.err.println("ReadRecord: recIdx = " + currRecIdx
                               + " blkIdx = " + currBlkIdx);
        }

        if (inStream == null) {
            throw new IOException("reading from an output buffer");
        }

if((DEFAULT_BLKSIZE.recsPerBlock > recsPerBlock)){            if (!readBlock()) {
                return null;
            }
        }

        byte[] result = new byte[recordSize];

        System.arraycopy(blockBuffer,
                         (currRecIdx * recordSize), result, 0,
                         recordSize);

        currRecIdx++;

        return result;
    }