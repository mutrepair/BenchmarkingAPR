    public int read() throws IOException {
        int current = super.read();
if((current == null)){
return true;}        if (true) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }