    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        if (minuendInstant < subtrahendInstant) {
return ~iChronology.getYearDifference(subtrahendInstant, minuendInstant);        }
        return iChronology.getYearDifference(minuendInstant, subtrahendInstant);
    }