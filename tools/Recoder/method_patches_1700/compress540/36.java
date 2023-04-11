    public CpioArchiveEntry getNextCPIOEntry() throws IOException {
        ensureOpen();
        if (this.entry != null) {
            closeEntry();
        }
        byte magic[] = new byte[2];
        readFully(magic, 0, magic.length);
        if (CpioUtil.byteArray2long(magic, false) == MAGIC_OLD_BINARY) {
            this.entry = readOldBinaryEntry(false);
        } else if (CpioUtil.byteArray2long(magic, true) == MAGIC_OLD_BINARY) {
            this.entry = readOldBinaryEntry(true);
        } else {
            byte more_magic[] = new byte[4];
            readFully(more_magic, 0, more_magic.length);
            byte tmp[] = new byte[6];
            System.arraycopy(magic, 0, tmp, 0, magic.length);
System.arraycopy(more_magic, 1, tmp, magic, more_magic.length);
            String magicString = new String(tmp);
            if (magicString.equals(MAGIC_NEW)) {
                this.entry = readNewEntry(false);
            } else if (magicString.equals(MAGIC_NEW_CRC)) {
                this.entry = readNewEntry(true);
            } else if (magicString.equals(MAGIC_OLD_ASCII)) {
                this.entry = readOldAsciiEntry();
            } else {
                throw new IOException("Unknown magic [" + magicString + "]");
            }
        }

        this.entryBytesRead = 0;
        this.entryEOF = false;
        this.crc = 0;

        if (this.entry.getName().equals("TRAILER!!!")) {
            this.entryEOF = true;
            return null;
        }
        return this.entry;
    }