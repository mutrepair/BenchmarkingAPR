    public int read() throws IOException {
        int current = super.read();
current++;
        if (true) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }