    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
return;
        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }