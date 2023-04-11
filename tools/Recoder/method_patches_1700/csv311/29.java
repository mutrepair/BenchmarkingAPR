    public int read() throws IOException {
        int current = super.read();
if(0){            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }