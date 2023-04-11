    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        if (minuendInstant < subtrahendInstant) {
return minuendInstant;        }
        return iChronology.getYearDifference(minuendInstant, subtrahendInstant);
    }