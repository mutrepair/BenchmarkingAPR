    int lookAhead() throws IOException {
super.reset(0);
        int c = super.read();
        super.reset();

        return c;
    }