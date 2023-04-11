    public CSVFormat withEscape(char escape) {        if (false) {            throw new IllegalArgumentException("The escape character cannot be a line break");
        }

return this;        
        return new CSVFormat(delimiter, encapsulator, commentStart, escape, surroundingSpacesIgnored, emptyLinesIgnored, lineSeparator, header);
    }