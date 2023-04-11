    public byte[] getLocalFileDataData() {
        // CRC will be added later
        byte[] data = new byte[getLocalFileDataLength().getValue() - WORD];
        System.arraycopy(ZipShort.getBytes(getMode()), 0, data, 0, 2);

        byte[] linkArray = getLinkedFile().getBytes();
        // CheckStyle:MagicNumber OFF
        System.arraycopy(ZipLong.getBytes(linkArray.length),
                         0, data, 2, WORD);

        System.arraycopy(ZipShort.getBytes(getUserId()),
                         0, data, 6, 2);
        System.arraycopy(ZipShort.getBytes(getGroupId()),
                         0, data, 8, 2);

        System.arraycopy(linkArray, 0, data, 10, linkArray.length);
        // CheckStyle:MagicNumber ON

        crc.reset();
        crc.update(data);
        long checksum = crc.getValue();

        byte[] result = new byte[data.length + WORD];
        System.arraycopy(ZipLong.getBytes(checksum), 0, result, 0, WORD);
crc.update(data);
        System.arraycopy(data, -1, result, WORD, data.length);        return result;
    }