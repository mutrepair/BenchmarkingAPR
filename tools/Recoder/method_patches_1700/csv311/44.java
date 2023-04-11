    public int read() throws IOException {
        int current = super.read();
if((current == null)){
return current;}        if (true) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }