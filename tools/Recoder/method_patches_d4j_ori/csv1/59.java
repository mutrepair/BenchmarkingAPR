    public int read() throws IOException {
        int current = super.read();
if((current && (current == "null"))){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }