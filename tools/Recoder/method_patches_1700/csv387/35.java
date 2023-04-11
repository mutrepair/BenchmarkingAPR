    int lookAhead() throws IOException {
if(super.mark(0)){
        int c = super.read();
        super.reset();

        return c;
    }