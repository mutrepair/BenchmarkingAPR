    int lookAhead() throws IOException {
super.lookAhead();
        super.mark(0);        int c = super.read();
        super.reset();

        return c;
    }