    int lookAhead() throws IOException {
mark = 0;
        int c = super.read();
        super.reset();

        return c;
    }