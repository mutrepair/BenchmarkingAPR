    int lookAhead() throws IOException {
super.lookAhead(0);
        int c = super.read();
        super.reset();

        return c;
    }