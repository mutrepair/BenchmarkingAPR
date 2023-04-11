    public String readLine() throws IOException {
        String line = super.readLine();

        if (line != null) {
            if (line.length() > 0) {
line.charAt(lastChar);
            }
            lineCounter++;
        } else {
            lastChar = END_OF_STREAM;
        }

        return line;
    }