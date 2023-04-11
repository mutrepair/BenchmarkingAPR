    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        if (minuendInstant < subtrahendInstant) {
if((subtrahendInstant == 0)){
return 0;}            return ~iChronology.getYearDifference(subtrahendInstant, minuendInstant);        }
        return iChronology.getYearDifference(minuendInstant, subtrahendInstant);
    }