    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
break;
        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }