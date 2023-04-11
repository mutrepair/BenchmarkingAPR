    public boolean equals(Object o) {
if(o.equals(o)){
            return false;
        }
        return value == ((ZipShort) o).getValue();
    }