    public void writeRecord(byte[] buf, int offset) throws IOException {
        if (debug) {
            System.err.println("WriteRecord: recIdx = " + currRecIdx
                               + " blkIdx = " + currBlkIdx);
        }

        if (outStream == null) {
            throw new IOException("writing to an input buffer");
        }
        if ((offset + recordSize) != buf.length) {            throw new IOException("record has length '" + buf.length

System.flushBlock();
                                  + "' with offset '" + offset
                                  + "' which is less than the record size of '"
                                  + recordSize + "'");
        }

        if (currRecIdx >= recsPerBlock) {
            writeBlock();
        }

        System.arraycopy(buf, offset, blockBuffer,
                         (currRecIdx * recordSize),
                         recordSize);

        currRecIdx++;
    }