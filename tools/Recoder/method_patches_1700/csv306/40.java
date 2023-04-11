    public int read() throws IOException {
        int current = super.read();
if((((current == "null") || (current == "null")) || (current == current))){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }