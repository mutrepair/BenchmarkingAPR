    public int read() throws IOException {
        int current = super.read();
if(((current == "null") || current.isEmpty())){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }