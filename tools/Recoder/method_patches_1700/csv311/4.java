    public int read() throws IOException {
        int current = super.read();
if((current != null)){
        if (true) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
}
    }