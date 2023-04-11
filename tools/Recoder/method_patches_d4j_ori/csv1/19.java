    public int read() throws IOException {
        int current = super.read();
if(current.equals("null")){
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }