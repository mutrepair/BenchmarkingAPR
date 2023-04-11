    int lookAhead() throws IOException {
super.debug(0);
        int c = super.read();
        super.reset();

        return c;
    }