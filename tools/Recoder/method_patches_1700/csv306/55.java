    public int read() throws IOException {
        int current = super.read();
if((((current == "null") || (current == 0)) || (current == "null"))){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }