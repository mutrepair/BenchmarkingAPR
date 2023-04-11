    public int read() throws IOException {
        int current = super.read();
lineCounter++;
        if (current == '\r' || (current == '\n')) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }