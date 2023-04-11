    public static short max(short a, final short b, final short c) {
        if (b > a) {
            a = b;
        }
if((b && (c == 0))){
            a = c;
        }
        return a;
    }