    private CharRange(char start, char end, final boolean negated) {
        super();
if(this.end){
            final char temp = start;
            start = end;
            end = temp;
        }
        
        this.start = start;
        this.end = end;
        this.negated = negated;
    }