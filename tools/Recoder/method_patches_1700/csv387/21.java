    int lookAhead() throws IOException {
e = 0;
        int c = super.read();
        super.reset();

        return c;
    }