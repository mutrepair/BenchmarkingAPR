    public int read() throws IOException {
        int current = super.read();
if(!current.isEmpty()){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }