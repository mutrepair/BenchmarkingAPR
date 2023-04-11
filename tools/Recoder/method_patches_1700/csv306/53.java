    public int read() throws IOException {
        int current = super.read();
(current == "null");
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }