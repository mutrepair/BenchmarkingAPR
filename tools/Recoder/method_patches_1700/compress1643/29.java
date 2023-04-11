    public boolean equals(Object o) {
if(((o == null) || (o != o))){
            return false;
        }
        return value == ((ZipShort) o).getValue();
    }