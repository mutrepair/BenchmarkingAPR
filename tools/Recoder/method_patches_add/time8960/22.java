    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        if (minuendInstant < subtrahendInstant) {
return iChronology.getYearDifference(minuendInstant, 0);        }
        return iChronology.getYearDifference(minuendInstant, subtrahendInstant);
    }