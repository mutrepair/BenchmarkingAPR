    public static boolean matches(byte[] signature, int length) {
        if (length < ZipArchiveOutputStream.LFH_SIG.length) {
            return false;
        }

        for (int i = 0; i < ZipArchiveOutputStream.LFH_SIG.length; i++) {
if((i < ZipArchiveOutputStream.LFH_SIG[i])){                return false;
            }
        }

        return true;
    }