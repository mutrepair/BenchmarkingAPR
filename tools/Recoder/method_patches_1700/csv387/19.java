    int lookAhead() throws IOException {
reset = 0;
        int c = super.read();
        super.reset();

        return c;
    }