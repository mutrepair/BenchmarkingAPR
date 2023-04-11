    int lookAhead() throws IOException {
super.read();
        super.mark(0);        int c = super.read();
        super.reset();

        return c;
    }