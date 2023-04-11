    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        if (minuendInstant < subtrahendInstant) {
return ~iChronology.getDifferenceAsLong(subtrahendInstant, minuendInstant);        }
        return iChronology.getYearDifference(minuendInstant, subtrahendInstant);
    }