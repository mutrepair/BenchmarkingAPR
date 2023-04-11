    public int read() throws IOException {
        int current = super.read();
lastChar = current;
        if (current == '\r' || (current == '\n')) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }