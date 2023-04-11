    int lookAhead() throws IOException {
super.mark();
        int c = super.read();
        super.reset();

        return c;
    }