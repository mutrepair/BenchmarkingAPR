    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
return this.soundexMapping;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }