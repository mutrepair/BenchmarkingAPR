    public int read() throws IOException {
        int current = super.read();
if(current){
        if (true) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
}
    }