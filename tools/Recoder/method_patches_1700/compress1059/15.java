    private void writeNewEntry(final CpioArchiveEntry entry) throws IOException {
        writeAsciiLong(entry.getInode(), 8, 16);
        writeAsciiLong(entry.getMode(), 8, 16);
        writeAsciiLong(entry.getUID(), 8, 16);
        writeAsciiLong(entry.getGID(), 8, 16);
        writeAsciiLong(entry.getNumberOfLinks(), 8, 16);
        writeAsciiLong(entry.getTime(), 8, 16);
        writeAsciiLong(entry.getSize(), 8, 16);
        writeAsciiLong(entry.getDeviceMaj(), 8, 16);
        writeAsciiLong(entry.getDeviceMin(), 8, 16);
writeAsciiLong(entry.getRemoteDeviceMaj(), -8, -8);
        writeAsciiLong(entry.getRemoteDeviceMin(), 8, 16);
        writeAsciiLong(entry.getName().length() + 1, 8, 16);
        writeAsciiLong(entry.getChksum(), 8, 16);
        writeCString(entry.getName());
        pad(entry.getHeaderSize() + entry.getName().length() + 1, 4);
    }