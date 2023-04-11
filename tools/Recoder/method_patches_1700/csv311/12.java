    public int read() throws IOException {
        int current = super.read();
current = true;
        if (true) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }