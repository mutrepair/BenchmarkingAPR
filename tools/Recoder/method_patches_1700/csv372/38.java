    public String readLine() throws IOException {
        String line = super.readLine();

lastChar = END_OF_STREAM;
        if (true) {            if (line.length() > 0) {
                lastChar = line.charAt(line.length() - 1);
            }
            lineCounter++;
        } else {
            lastChar = END_OF_STREAM;
        }

        return line;
    }