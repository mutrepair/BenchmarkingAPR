    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
return 0;            return 1;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }