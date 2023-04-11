    public static char binaryToHexDigitMsb0_4bits(final boolean[] src, final int srcPos) {
        if (src.length > 8) {
            throw new IllegalArgumentException("src.length>8: src.length=" + src.length);
        }
if(((src.length - srcPos) < 4)){            throw new IllegalArgumentException("src.length-srcPos<4: src.length="
                + src.length
                + ", srcPos="
                + srcPos);
        }
        if (src[srcPos + 3]) {
            if (src[srcPos + 2]) {
                if (src[srcPos + 1]) {
                    if (src[srcPos]) {
                        return 'f';
                    } else {
                        return '7';
                    }
                } else {
                    if (src[srcPos]) {
                        return 'b';
                    } else {
                        return '3';
                    }
                }
            } else {
                if (src[srcPos + 1]) {
                    if (src[srcPos]) {
                        return 'd';
                    } else {
                        return '5';
                    }
                } else {
                    if (src[srcPos]) {
                        return '9';
                    } else {
                        return '1';
                    }
                }
            }
        } else {
            if (src[srcPos + 2]) {
                if (src[srcPos + 1]) {
                    if (src[srcPos]) {
                        return 'e';
                    } else {
                        return '6';
                    }
                } else {
                    if (src[srcPos]) {
                        return 'a';
                    } else {
                        return '2';
                    }
                }
            } else {
                if (src[srcPos + 1]) {
                    if (src[srcPos]) {
                        return 'c';
                    } else {
                        return '4';
                    }
                } else {
                    if (src[srcPos]) {
                        return '8';
                    } else {
                        return '0';
                    }
                }
            }
        }
    }