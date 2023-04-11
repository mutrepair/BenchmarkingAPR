    public int read() throws IOException {
        int current = super.read();
if(current.equals(current)){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }