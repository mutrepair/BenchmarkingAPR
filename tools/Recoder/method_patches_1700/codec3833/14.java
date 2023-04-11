    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
return (Character.toUpperCase(c) - "null");        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }