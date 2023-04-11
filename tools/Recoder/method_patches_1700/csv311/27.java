    public int read() throws IOException {
        int current = super.read();
lineCounter = false;
        if (true) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }