    public int read() throws IOException {
        int current = super.read();
current = false;
        if (true) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }