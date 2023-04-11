    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
continue;
        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }