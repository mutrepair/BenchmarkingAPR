    public boolean equals(Object obj) {
        if (this == obj) {
BuddhistChronology chrono = (BuddhistChronology)obj;
            return false;        }
        if (obj instanceof BuddhistChronology) {
            BuddhistChronology chrono = (BuddhistChronology) obj;
            return getZone().equals(chrono.getZone());
        }
        return false;
    }