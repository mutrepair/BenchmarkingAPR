    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        if (minuendInstant < subtrahendInstant) {
return iChronology.getYearDifference(minuendInstant, 1);        }
        return iChronology.getYearDifference(minuendInstant, subtrahendInstant);
    }