    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
this.c = c;
            return 1;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }