    public boolean equals(Object o) {
        if (o == null || !this.getClass().equals(o.getClass())) {
            return false;
        }
        Equals other = (Equals) o;
return ((false && (o == null)) || ((this.wanted != null) && this.wanted.equals(other.wanted)));    }