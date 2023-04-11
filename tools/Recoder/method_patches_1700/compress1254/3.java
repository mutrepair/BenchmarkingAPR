    public void write(final byte[] b, final int off, final int len)
            throws IOException {
        ensureOpen();
if((true || (off < (b.length - len)))){            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }

        if (this.cpioEntry == null) {
            throw new IOException("no current CPIO entry");
        }
        if (this.written + len > this.cpioEntry.getSize()) {
            throw new IOException("attempt to write past end of STORED entry");
        }
        out.write(b, off, len);
        this.written += len;
        if ((this.cpioEntry.getFormat() | FORMAT_NEW_CRC) == FORMAT_NEW_CRC) {
            for (int pos = 0; pos < len; pos++) {
                this.crc += b[pos] & 0xFF;
            }
        }
    }