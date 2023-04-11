    public int read() throws IOException {
        int current = super.read();
if((current == current)){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }