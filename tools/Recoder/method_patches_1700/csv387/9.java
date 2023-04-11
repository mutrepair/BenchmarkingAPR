    int lookAhead() throws IOException {
this.mark(0);
        int c = super.read();
        super.reset();

        return c;
    }