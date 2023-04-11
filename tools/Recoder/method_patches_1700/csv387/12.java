    int lookAhead() throws IOException {
super.lookAhead();
        int c = super.read();
        super.reset();

        return c;
    }