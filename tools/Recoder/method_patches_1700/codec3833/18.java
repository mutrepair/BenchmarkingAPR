    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
return false;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }