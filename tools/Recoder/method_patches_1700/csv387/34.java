    int lookAhead() throws IOException {
return;
        super.mark(0);        int c = super.read();
        super.reset();

        return c;
    }