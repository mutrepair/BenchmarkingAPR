    public int read() throws IOException {
        int current = super.read();
if((current != null)){
return 0;}        if (true) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }