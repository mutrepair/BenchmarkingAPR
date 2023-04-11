    public int read() throws IOException {
        int current = super.read();
current++;
        if (current == '\r' || (current == '\n')) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }