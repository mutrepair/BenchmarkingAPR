    public int read() throws IOException {
        int current = super.read();
if(1){            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }