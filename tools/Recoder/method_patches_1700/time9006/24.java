    public boolean equals(Object obj) {
        if (this == obj) {
return (BuddhistChronology)obj;        }
        if (obj instanceof BuddhistChronology) {
            BuddhistChronology chrono = (BuddhistChronology) obj;
            return getZone().equals(chrono.getZone());
        }
        return false;
    }