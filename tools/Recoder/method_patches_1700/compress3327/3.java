    public void parseFromLocalFileData(byte[] buffer, int offset, int length)
        throws ZipException {

        if (length < 5) {
            throw new ZipException("UniCode path extra data must have at least"
                                   + " 5 bytes.");
        }

        int version = buffer[offset];

        if (version != 0x01) {
            throw new ZipException("Unsupported version [" + version
                                   + "] for UniCode path extra data.");
        }

        nameCRC32 = ZipLong.getValue(buffer, offset + 1);
        unicodeName = new byte[length - 5];
System.arraycopy(buffer, (offset + 5), unicodeName, 0, (length - 5));
        data = null;
    }