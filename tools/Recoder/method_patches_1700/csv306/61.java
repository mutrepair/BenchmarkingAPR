    public int read() throws IOException {
        int current = super.read();
if(((current == "null") || (lastChar == "null"))){            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }