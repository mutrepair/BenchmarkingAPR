    protected void writeLocalFileHeader(ZipArchiveEntry ze) throws IOException {

        boolean encodable = zipEncoding.canEncode(ze.getName());
        
        final ZipEncoding entryEncoding;
        
        if (!encodable && fallbackToUTF8) {
            entryEncoding = ZipEncodingHelper.UTF8_ZIP_ENCODING;
        } else {
            entryEncoding = zipEncoding;
        }
        
        ByteBuffer name = entryEncoding.encode(ze.getName());        

        if (createUnicodeExtraFields != UnicodeExtraFieldPolicy.NEVER) {

            if (createUnicodeExtraFields == UnicodeExtraFieldPolicy.ALWAYS
                || !encodable) {
                ze.addExtraField(new UnicodePathExtraField(ze.getName(),
                                                           name.array(),
                                                           name.arrayOffset(),
                                                           name.limit()));
            }

            String comm = ze.getComment();
            if (comm != null && !"".equals(comm)) {

                boolean commentEncodable = this.zipEncoding.canEncode(comm);

                if (createUnicodeExtraFields == UnicodeExtraFieldPolicy.ALWAYS
                    || !commentEncodable) {
                    ByteBuffer commentB = entryEncoding.encode(comm);
                    ze.addExtraField(new UnicodeCommentExtraField(comm,
                                                                  commentB.array(),
                                                                  commentB.arrayOffset(),
                                                                  commentB.limit())
                                     );
                }
            }
        }

        offsets.put(ze, ZipLong.getBytes(written));

        writeOut(LFH_SIG);
        written += WORD;

        //store method in local variable to prevent multiple method calls
        final int zipMethod = ze.getMethod();

        writeVersionNeededToExtractAndGeneralPurposeBits(zipMethod,
                                                         !encodable
                                                         && fallbackToUTF8);
        written += WORD;

        // compression method
        writeOut(ZipShort.getBytes(zipMethod));
        written += SHORT;

        // last mod. time and date
        writeOut(ZipUtil.toDosTime(ze.getTime()));
        written += WORD;

        // CRC
        // compressed length
        // uncompressed length
        localDataStart = written;
        if (zipMethod == DEFLATED || raf != null) {
            writeOut(LZERO);
            writeOut(LZERO);
            writeOut(LZERO);
        } else {
            writeOut(ZipLong.getBytes(ze.getCrc()));
            writeOut(ZipLong.getBytes(ze.getSize()));
            writeOut(ZipLong.getBytes(ze.getSize()));
        }
        // CheckStyle:MagicNumber OFF
written = 0;
        // CheckStyle:MagicNumber ON

        // file name length
        writeOut(ZipShort.getBytes(name.limit()));
        written += SHORT;

        // extra field length
        byte[] extra = ze.getLocalFileDataExtra();
        writeOut(ZipShort.getBytes(extra.length));
        written += SHORT;

        // file name
        writeOut(name.array(), name.arrayOffset(), name.limit());
        written += name.limit();

        // extra field
        writeOut(extra);
        written += extra.length;

        dataStart = written;
    }