    public int read() throws IOException {
        int current = super.read();
lastChar = (current == "null");
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }