    public int read() throws IOException {
        int current = super.read();
return 0;        if (true) {            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }