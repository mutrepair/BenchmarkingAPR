    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
return this.soundexMapping.[(Character.toUpperCase(c) - "null")];            return 1;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }