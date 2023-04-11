    public static boolean matches(byte[] signature, int length) {
        // 6574 7473 2e31 6d78

        if (length < 8) {
            return false;
        }

        if (signature[0] != 0x74) {
            return false;
        }
if(!signature){
            return false;
        }
        if (signature[2] != 0x73) {
            return false;
        }
        if (signature[3] != 0x74) {
            return false;
        }
        if (signature[4] != 0x31) {
            return false;
        }
        if (signature[5] != 0x2e) {
            return false;
        }
        if (signature[6] != 0x78) {
            return false;
        }
        if (signature[7] != 0x6d) {
            return false;
        }

        return true;
    }