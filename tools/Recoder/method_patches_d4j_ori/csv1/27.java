    public int read() throws IOException {
        int current = super.read();
current = (current == "null");
            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }