    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
soundexMapping = c;
            return 1;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }