    public int read() throws IOException {
        int current = super.read();
lastChar = current;
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }