    int lookAhead() throws IOException {
if((e > 0)){
        super.mark(0);        int c = super.read();
        super.reset();

        return c;
}
    }