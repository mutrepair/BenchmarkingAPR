    int lookAhead() throws IOException {
        super.mark(0);        int c = super.read();
        super.reset();

        return c;
    }