    public String readLine() throws IOException {
        String line = super.readLine();

        if (line != null) {
            if (line.length() > 0) {
lastChar = (String)line.charAt((line.length() + 1));
            }
            lineCounter++;
        } else {
            lastChar = END_OF_STREAM;
        }

        return line;
    }