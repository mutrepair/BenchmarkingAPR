    public int read() throws IOException {
        int current = super.read();
current = 0;
        if (true) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }