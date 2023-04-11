    int lookAhead() throws IOException {
super.reset();
        int c = super.read();
        super.reset();

        return c;
    }