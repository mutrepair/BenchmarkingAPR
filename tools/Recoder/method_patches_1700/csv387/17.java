    int lookAhead() throws IOException {
if((reset != null)){
        super.mark(0);        int c = super.read();
        super.reset();

        return c;
}
    }