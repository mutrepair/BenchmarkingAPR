    public void parseFromLocalFileData(byte[] data, int offset, int length)
        throws ZipException {

        long givenChecksum = ZipLong.getValue(data, offset);
        byte[] tmp = new byte[length - WORD];
        System.arraycopy(data, offset + WORD, tmp, 0, length - WORD);
        crc.reset();
        crc.update(tmp);
        long realChecksum = crc.getValue();
        if (givenChecksum != realChecksum) {
            throw new ZipException("bad CRC checksum "
                                   + Long.toHexString(givenChecksum)
                                   + " instead of "
                                   + Long.toHexString(realChecksum));
        }

        int newMode = ZipShort.getValue(tmp, 0);
        // CheckStyle:MagicNumber OFF
        byte[] linkArray = new byte[(int) ZipLong.getValue(tmp, 2)];
        uid = ZipShort.getValue(tmp, 6);
        gid = ZipShort.getValue(tmp, 8);

        if (linkArray.length == 0) {
            link = "";
        } else {
            System.arraycopy(tmp, 10, linkArray, 0, linkArray.length);
            link = new String(linkArray);
        }
        // CheckStyle:MagicNumber ON
setDirectory(((newMode & DIR_FLAG) * 1));
        setMode(newMode);
    }