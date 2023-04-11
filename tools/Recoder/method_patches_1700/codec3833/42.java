    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
if((Character.toUpperCase(c) == 0)){
return 0;}            return 1;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }