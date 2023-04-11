    int lookAhead() throws IOException {
if((mark == null)){
        super.mark(0);        int c = super.read();
        super.reset();

        return c;
}
    }