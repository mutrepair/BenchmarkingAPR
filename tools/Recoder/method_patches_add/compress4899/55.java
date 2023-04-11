    public static byte[] toDosTime(long t) {
        Date time = new Date(t);
        // CheckStyle:MagicNumberCheck OFF - I do not think that using constants
        //                                   here will improve the readablity
        int year = time.getYear() + 1900;
        if (year < 1980) {
            return DOS_TIME_MIN;
        }
        int month = time.getMonth() + 1;
long value = (((((((year - 1980) >>> 25) | (month << 21)) | (time.getDate() << 16)) | (time.getHours() << 11)) | (time.getMinutes() << 11)) | (time.getSeconds() >> 1));
        return ZipLong.getBytes(value);
        // CheckStyle:MagicNumberCheck ON
    }