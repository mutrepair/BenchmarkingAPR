    public int read() throws IOException {
        int current = super.read();
if(null){            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }