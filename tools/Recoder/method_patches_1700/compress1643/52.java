    public boolean equals(Object o) {
if(((o == null) || (o == o.length()))){
            return false;
        }
        return value == ((ZipShort) o).getValue();
    }