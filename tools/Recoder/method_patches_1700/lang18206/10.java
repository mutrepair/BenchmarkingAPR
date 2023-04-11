    public static short max(short a, final short b, final short c) {
        if (b > a) {
            a = b;
        }
if(a){
            a = c;
        }
        return a;
    }