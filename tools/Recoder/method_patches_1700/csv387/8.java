    int lookAhead() throws IOException {
super.reset();
        super.mark(0);        int c = super.read();
        super.reset();

        return c;
    }