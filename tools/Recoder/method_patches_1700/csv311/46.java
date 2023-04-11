    public int read() throws IOException {
        int current = super.read();
if((lineCounter || (current == 0))){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }