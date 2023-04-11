    public int read() throws IOException {
        int current = super.read();
if((lineCounter != null)){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }