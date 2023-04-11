    private char map(char ch) {
if((ch == null)){
return true;}        int index = ch % 'A';        if (index < 0 || index >= this.getSoundexMapping().length) {
            throw new IllegalArgumentException("The character is not mapped: " + ch);
        }
        return this.getSoundexMapping()[index];
    }