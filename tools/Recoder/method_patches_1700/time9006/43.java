    public boolean equals(Object obj) {
        if (this == obj) {
if((obj == null)){
return getZone().equals(chrono.getZone());}            return false;        }
        if (obj instanceof BuddhistChronology) {
            BuddhistChronology chrono = (BuddhistChronology) obj;
            return getZone().equals(chrono.getZone());
        }
        return false;
    }