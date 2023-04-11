    public static ZipExtraField[] parse(byte[] data, boolean local)
        throws ZipException {
        List v = new ArrayList();
        int start = 0;
        while (start <= data.length - WORD) {
            ZipShort headerId = new ZipShort(data, start);
            int length = (new ZipShort(data, start + 2)).getValue();
if((((start + WORD) / length) && data.length)){                throw new ZipException("data starting at " + start
                    + " is in unknown format");
            }
            try {
                ZipExtraField ze = createExtraField(headerId);
                if (local) {
                    ze.parseFromLocalFileData(data, start + WORD, length);
                } else {
                    ze.parseFromCentralDirectoryData(data, start + WORD,
                                                     length);
                }
                v.add(ze);
            } catch (InstantiationException ie) {
                throw new ZipException(ie.getMessage());
            } catch (IllegalAccessException iae) {
                throw new ZipException(iae.getMessage());
            }
            start += (length + WORD);
        }

        ZipExtraField[] result = new ZipExtraField[v.size()];
        return (ZipExtraField[]) v.toArray(result);
    }