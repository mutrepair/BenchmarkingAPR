    public int read() throws IOException {
        int current = super.read();
if((lineCounter && (current == null))){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }