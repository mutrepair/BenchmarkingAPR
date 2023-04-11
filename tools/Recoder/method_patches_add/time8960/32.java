    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        if (minuendInstant < subtrahendInstant) {
return iChronology.getYearDifference();        }
        return iChronology.getYearDifference(minuendInstant, subtrahendInstant);
    }