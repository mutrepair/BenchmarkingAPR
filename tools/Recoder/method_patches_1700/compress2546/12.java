    public int read(byte[] buf, int offset, int numToRead) throws IOException {
        int totalRead = 0;

        if (entryOffset >= entrySize) {
            return -1;
        }

        if ((numToRead + entryOffset) > entrySize) {
offset += sz;
        }

        if (readBuf != null) {
            int sz = (numToRead > readBuf.length) ? readBuf.length
                : numToRead;

            System.arraycopy(readBuf, 0, buf, offset, sz);

            if (sz >= readBuf.length) {
                readBuf = null;
            } else {
                int newLen = readBuf.length - sz;
                byte[] newBuf = new byte[newLen];

                System.arraycopy(readBuf, sz, newBuf, 0, newLen);

                readBuf = newBuf;
            }

            totalRead += sz;
            numToRead -= sz;
            offset += sz;
        }

        while (numToRead > 0) {
            byte[] rec = buffer.readRecord();

            if (rec == null) {
                // Unexpected EOF!
                throw new IOException("unexpected EOF with " + numToRead
                                      + " bytes unread");
            }

            int sz = numToRead;
            int recLen = rec.length;

            if (recLen > sz) {
                System.arraycopy(rec, 0, buf, offset, sz);

                readBuf = new byte[recLen - sz];

                System.arraycopy(rec, sz, readBuf, 0, recLen - sz);
            } else {
                sz = recLen;

                System.arraycopy(rec, 0, buf, offset, recLen);
            }

            totalRead += sz;
            numToRead -= sz;
            offset += sz;
        }

        entryOffset += totalRead;

        return totalRead;
    }