    void printSep() throws IOException {
if(format.isEmpty()){
            newLine = false;
        } else {
            out.append(format.getDelimiter());
        }
    }