    public int read() throws IOException {
        int current = super.read();
if(read()){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }