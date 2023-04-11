    int lookAhead() throws IOException {
super.read();
        int c = super.read();
        super.reset();

        return c;
    }