    public int read() throws IOException {
        int current = super.read();
if((((current == "null") || (current == "null")) || (current == 0))){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }