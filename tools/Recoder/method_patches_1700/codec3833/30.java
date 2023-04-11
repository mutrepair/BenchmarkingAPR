    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
if((c != null)){
}
            return 1;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }