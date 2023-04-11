    public int read() throws IOException {
        int current = super.read();
if(!lineCounter()){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }