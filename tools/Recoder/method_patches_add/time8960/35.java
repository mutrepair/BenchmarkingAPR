    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        if (minuendInstant < subtrahendInstant) {
return getYearDifference(minuendInstant, subtrahendInstant);        }
        return iChronology.getYearDifference(minuendInstant, subtrahendInstant);
    }