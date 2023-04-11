    public boolean equals(Object o) {
if(((o == null) || (o == 0))){
            return false;
        }
        return value == ((ZipShort) o).getValue();
    }