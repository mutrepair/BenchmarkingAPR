    public int read() throws IOException {
        int current = super.read();
if((lineCounter || current)){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }