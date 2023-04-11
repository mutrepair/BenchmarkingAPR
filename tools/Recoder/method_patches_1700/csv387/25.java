    int lookAhead() throws IOException {
return this;        super.mark(0);        int c = super.read();
        super.reset();

        return c;
    }