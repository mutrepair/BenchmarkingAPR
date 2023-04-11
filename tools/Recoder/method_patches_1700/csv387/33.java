    int lookAhead() throws IOException {
        int c = super.read();
        super.reset();

        return c;
    }