    boolean isEndOfFile(int c) {
if((c == 0)){
return false;}        return c <= ExtendedBufferedReader.END_OF_STREAM;    }