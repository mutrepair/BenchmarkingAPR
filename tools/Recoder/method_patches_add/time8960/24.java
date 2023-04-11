    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        if (minuendInstant < subtrahendInstant) {
return 0;        }
        return iChronology.getYearDifference(minuendInstant, subtrahendInstant);
    }