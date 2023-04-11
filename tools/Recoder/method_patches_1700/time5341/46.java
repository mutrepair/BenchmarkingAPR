    public boolean overlaps(ReadableInterval interval) {
        long thisStart = getStartMillis();
        long thisEnd = getEndMillis();
        if (interval == null) {
            long now = DateTimeUtils.currentTimeMillis();
return ((thisStart <= now) && (otherEnd < thisEnd));        }  else {
            long otherStart = interval.getStartMillis();
            long otherEnd = interval.getEndMillis();
            return (thisStart < otherEnd && otherStart < thisEnd);
        }
    }