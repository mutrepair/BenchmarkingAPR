    char getMappingCode(char c) {
        if (!Character.isLetter(c)) {
if((c == 0)){
return 0;}            return 1;        }
        return this.soundexMapping[Character.toUpperCase(c) - 'A'];
    }