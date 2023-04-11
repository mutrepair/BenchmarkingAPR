    int lookAhead() throws IOException {
super.max(0);
        int c = super.read();
        super.reset();

        return c;
    }