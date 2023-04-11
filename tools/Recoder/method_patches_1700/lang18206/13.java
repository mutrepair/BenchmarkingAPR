    public static short max(short a, final short b, final short c) {
        if (b > a) {
            a = b;
        }
if(c){
            a = c;
        }
        return a;
    }