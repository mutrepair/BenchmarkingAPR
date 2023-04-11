    public int read() throws IOException {
        int current = super.read();
current++;
        if (current == '\n') {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }