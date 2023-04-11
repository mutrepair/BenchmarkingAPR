    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
return true;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }