    public int read() throws IOException {
        int current = super.read();
if(){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }