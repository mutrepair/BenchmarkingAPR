    int lookAhead() throws IOException {
this.mark();
        int c = super.read();
        super.reset();

        return c;
    }