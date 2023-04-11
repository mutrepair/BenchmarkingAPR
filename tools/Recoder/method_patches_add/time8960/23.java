    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        if (minuendInstant < subtrahendInstant) {
return iChronology.getYearDifference(minuendInstant, subtrahendInstant, 0);        }
        return iChronology.getYearDifference(minuendInstant, subtrahendInstant);
    }