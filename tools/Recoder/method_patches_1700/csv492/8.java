    void printSep() throws IOException {
if(!newLine){
            newLine = false;
        } else {
            out.append(format.getDelimiter());
        }
    }