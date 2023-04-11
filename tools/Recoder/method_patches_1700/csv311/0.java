    public int read() throws IOException {
        int current = super.read();
if(false){            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }