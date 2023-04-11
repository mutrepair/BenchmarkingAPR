    public int read() throws IOException {
        int current = super.read();
if(!lastChar){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }