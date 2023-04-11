    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        if (minuendInstant < subtrahendInstant) {
return iChronology.getYearDifference(minuendInstant, minuendInstant);        }
        return iChronology.getYearDifference(minuendInstant, subtrahendInstant);
    }