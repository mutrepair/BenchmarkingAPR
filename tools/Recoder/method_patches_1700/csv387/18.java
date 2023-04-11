    int lookAhead() throws IOException {
super.mark(2);
        int c = super.read();
        super.reset();

        return c;
    }