    int lookAhead() throws IOException {
super.read(0);
        int c = super.read();
        super.reset();

        return c;
    }