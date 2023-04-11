    public int read() throws IOException {
        int current = super.read();
if((current == lineCounter)){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }