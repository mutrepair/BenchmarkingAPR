    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
continue;
            return 1;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }