    int lookAhead() throws IOException {
read = 0;
        int c = super.read();
        super.reset();

        return c;
    }