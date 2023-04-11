    int lookAhead() throws IOException {
return this;        int c = super.read();
        super.reset();

        return c;
    }