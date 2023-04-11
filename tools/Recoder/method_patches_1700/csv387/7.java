    int lookAhead() throws IOException {
super.mark(1);
        int c = super.read();
        super.reset();

        return c;
    }