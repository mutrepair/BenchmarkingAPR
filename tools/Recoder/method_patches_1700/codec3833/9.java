    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
return -2;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }