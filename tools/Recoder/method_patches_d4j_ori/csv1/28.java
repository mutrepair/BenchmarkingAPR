    public int read() throws IOException {
        int current = super.read();
current++;
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }