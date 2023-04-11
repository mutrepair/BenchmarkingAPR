    public int read() throws IOException {
        int current = super.read();
if(current){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }