    public boolean equals(Object o) {
if(((o == null) && (o == value))){
            return false;
        }
        return value == ((ZipShort) o).getValue();
    }