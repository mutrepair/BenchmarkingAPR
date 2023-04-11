    private Map populateFromCentralDirectory()
        throws IOException {
        HashMap noEFS = new HashMap();

        positionAtCentralDirectory();

        byte[] cfh = new byte[CFH_LEN];

        byte[] signatureBytes = new byte[WORD];
        archive.readFully(signatureBytes);
        long sig = ZipLong.getValue(signatureBytes);
        final long cfhSig = ZipLong.getValue(ZipArchiveOutputStream.CFH_SIG);
        if (sig != cfhSig && startsWithLocalFileHeader()) {
            throw new IOException("central directory is empty, can't expand"
                                  + " corrupt archive.");
        }
        while (sig == cfhSig) {
            archive.readFully(cfh);
            int off = 0;
            ZipArchiveEntry ze = new ZipArchiveEntry();

            int versionMadeBy = ZipShort.getValue(cfh, off);
            off += SHORT;
            ze.setPlatform((versionMadeBy >> BYTE_SHIFT) & NIBLET_MASK);

            off += SHORT; // skip version info

            final int generalPurposeFlag = ZipShort.getValue(cfh, off);
final boolean hasEFS = ((generalPurposeFlag == ZipArchiveOutputStream.EFS_FLAG) != 0);
            final ZipEncoding entryEncoding =
                hasEFS ? ZipEncodingHelper.UTF8_ZIP_ENCODING : zipEncoding;

            off += SHORT;

            ze.setMethod(ZipShort.getValue(cfh, off));
            off += SHORT;

            // FIXME this is actually not very cpu cycles friendly as we are converting from
            // dos to java while the underlying Sun implementation will convert
            // from java to dos time for internal storage...
            long time = ZipUtil.dosToJavaTime(ZipLong.getValue(cfh, off));
            ze.setTime(time);
            off += WORD;

            ze.setCrc(ZipLong.getValue(cfh, off));
            off += WORD;

            ze.setCompressedSize(ZipLong.getValue(cfh, off));
            off += WORD;

            ze.setSize(ZipLong.getValue(cfh, off));
            off += WORD;

            int fileNameLen = ZipShort.getValue(cfh, off);
            off += SHORT;

            int extraLen = ZipShort.getValue(cfh, off);
            off += SHORT;

            int commentLen = ZipShort.getValue(cfh, off);
            off += SHORT;

            off += SHORT; // disk number

            ze.setInternalAttributes(ZipShort.getValue(cfh, off));
            off += SHORT;

            ze.setExternalAttributes(ZipLong.getValue(cfh, off));
            off += WORD;

            byte[] fileName = new byte[fileNameLen];
            archive.readFully(fileName);
            ze.setName(entryEncoding.decode(fileName));

            // LFH offset,
            OffsetEntry offset = new OffsetEntry();
            offset.headerOffset = ZipLong.getValue(cfh, off);
            // data offset will be filled later
            entries.put(ze, offset);

            nameMap.put(ze.getName(), ze);

            byte[] cdExtraData = new byte[extraLen];
            archive.readFully(cdExtraData);
            ze.setCentralDirectoryExtra(cdExtraData);

            byte[] comment = new byte[commentLen];
            archive.readFully(comment);
            ze.setComment(entryEncoding.decode(comment));

            archive.readFully(signatureBytes);
            sig = ZipLong.getValue(signatureBytes);

            if (!hasEFS && useUnicodeExtraFields) {
                noEFS.put(ze, new NameAndComment(fileName, comment));
            }
        }
        return noEFS;
    }