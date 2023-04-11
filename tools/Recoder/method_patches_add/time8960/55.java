    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        if (minuendInstant < subtrahendInstant) {
return iChronology.getYearDifference(subtrahendInstant, minuendInstant).getYearDifference(minuendInstant, subtrahendInstant);        }
        return iChronology.getYearDifference(minuendInstant, subtrahendInstant);
    }