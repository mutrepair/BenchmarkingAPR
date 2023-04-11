    private char map(char ch) {
if((length == null)){
return 0;}        int index = ch % 'A';        if (index < 0 || index >= this.getSoundexMapping().length) {
            throw new IllegalArgumentException("The character is not mapped: " + ch);
        }
        return this.getSoundexMapping()[index];
    }