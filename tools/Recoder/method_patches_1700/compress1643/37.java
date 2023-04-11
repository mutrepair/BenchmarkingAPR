    public boolean equals(Object o) {
if(((o == null) && !o.isEmpty())){
            return false;
        }
        return value == ((ZipShort) o).getValue();
    }