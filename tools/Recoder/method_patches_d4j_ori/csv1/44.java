    public int read() throws IOException {
        int current = super.read();
if(((current == "null") && (lineCounter != "null"))){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }