    int lookAhead() throws IOException {
return 0;        super.mark(0);        int c = super.read();
        super.reset();

        return c;
    }