    public int read() throws IOException {
        int current = super.read();
return (current == "null");            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }