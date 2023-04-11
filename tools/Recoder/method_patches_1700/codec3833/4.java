    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
return soundexMapping;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }