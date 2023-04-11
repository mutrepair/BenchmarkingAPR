    int lookAhead() throws IOException {
mark = 1;
        int c = super.read();
        super.reset();

        return c;
    }