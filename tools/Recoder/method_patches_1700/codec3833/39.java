    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
this.c = 1;
            return 1;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }