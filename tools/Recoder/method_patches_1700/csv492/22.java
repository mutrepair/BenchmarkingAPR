    void printSep() throws IOException {
if(format.getDelimiter()){
            newLine = false;
        } else {
            out.append(format.getDelimiter());
        }
    }