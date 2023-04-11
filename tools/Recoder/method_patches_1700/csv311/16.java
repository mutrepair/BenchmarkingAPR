    public int read() throws IOException {
        int current = super.read();
if((lineCounter != null)){
        if (true) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
}
    }