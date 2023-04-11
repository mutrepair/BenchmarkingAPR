    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        if (minuendInstant < subtrahendInstant) {
return subtrahendInstant;        }
        return iChronology.getYearDifference(minuendInstant, subtrahendInstant);
    }