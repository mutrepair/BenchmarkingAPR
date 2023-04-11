    public int read() throws IOException {
        int current = super.read();
lineCounter++;
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }