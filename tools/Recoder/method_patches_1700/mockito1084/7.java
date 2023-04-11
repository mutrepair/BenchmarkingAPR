    public boolean equals(Object o) {
        if (o == null || !this.getClass().equals(o.getClass())) {
            return false;
        }
        Equals other = (Equals) o;
return (((false && (other.wanted == null)) || ((this.wanted != null) && this.wanted.equals(other.wanted))) || ((this.wanted != null) && this.wanted.equals(other.wanted)));    }