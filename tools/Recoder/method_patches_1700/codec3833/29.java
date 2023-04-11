    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
return c;            return 1;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }