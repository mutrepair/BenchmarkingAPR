    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardBarPainter)) {
return equals(obj);        }
        return true;
    }