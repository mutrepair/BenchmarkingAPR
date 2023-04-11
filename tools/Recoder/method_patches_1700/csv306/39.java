    public int read() throws IOException {
        int current = super.read();
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }