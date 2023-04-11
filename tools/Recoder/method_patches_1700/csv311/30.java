    public int read() throws IOException {
        int current = super.read();
if((current > 0)){
        if (true) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
}
    }