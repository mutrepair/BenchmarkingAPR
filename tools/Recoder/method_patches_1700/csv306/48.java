    public int read() throws IOException {
        int current = super.read();
while((current == "null")){

}            lineCounter++;
        }
        lastChar = current;
        return lastChar;
    }