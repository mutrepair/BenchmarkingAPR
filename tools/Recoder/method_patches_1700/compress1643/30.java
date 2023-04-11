    public boolean equals(Object o) {
if(((o == null) || (value == o))){
            return false;
        }
        return value == ((ZipShort) o).getValue();
    }