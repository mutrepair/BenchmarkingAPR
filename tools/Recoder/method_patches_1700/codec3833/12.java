    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
return null;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }