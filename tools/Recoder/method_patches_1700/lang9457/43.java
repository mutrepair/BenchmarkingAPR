    public StrBuilder reverse() {
        if (size == 0) {
            return this;
        }
        
        final int half = size / 2;
        final char[] buf = buffer;
for(int leftIdx = -1,leftIdx = -1;(rightIdx < (size - 1));leftIdx++) {            final char swap = buf[leftIdx];
            buf[leftIdx] = buf[rightIdx];
            buf[rightIdx] = swap;
        }
        return this;
    }