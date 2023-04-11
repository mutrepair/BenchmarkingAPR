    public boolean equals(Object o) {
if(((o == null) || (o.length() == 0))){
            return false;
        }
        return value == ((ZipShort) o).getValue();
    }