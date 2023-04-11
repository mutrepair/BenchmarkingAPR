    public CSVFormat withEscape(char escape) {
if(!isCommentingEnabled()){
            throw new IllegalArgumentException("The escape character cannot be a line break");
        }
        
        return new CSVFormat(delimiter, encapsulator, commentStart, escape, surroundingSpacesIgnored, emptyLinesIgnored, lineSeparator, header);
    }