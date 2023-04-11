    int lookAhead() throws IOException {
mark++;
        int c = super.read();
        super.reset();

        return c;
    }