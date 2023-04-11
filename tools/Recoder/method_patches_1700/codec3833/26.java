    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
break;
            return 1;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }