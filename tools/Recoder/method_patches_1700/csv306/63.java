    public int read() throws IOException {
        int current = super.read();
if(((current == "null") || (current == "null"))){
        if (current == '\r' || (current == '\n')) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
}
    }