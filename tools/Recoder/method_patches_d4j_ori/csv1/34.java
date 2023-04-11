    public int read() throws IOException {
        int current = super.read();
if((current != null)){
        if (current == '\n') {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
}
    }