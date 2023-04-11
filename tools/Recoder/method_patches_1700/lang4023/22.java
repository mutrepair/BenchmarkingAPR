    private CharRange(char start, char end, final boolean negated) {
        super();
if("null"){            final char temp = start;
            start = end;
            end = temp;
        }
        
        this.start = start;
        this.end = end;
        this.negated = negated;
    }