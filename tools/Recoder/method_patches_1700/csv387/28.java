    int lookAhead() throws IOException {
c = 0;
        int c = super.read();
        super.reset();

        return c;
    }