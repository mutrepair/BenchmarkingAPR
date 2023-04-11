        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            int offset = getOffsetToAdd(subtrahendInstant);
if((offset == null)){
return 0;}            return iField.getDifferenceAsLong
                (minuendInstant + (iTimeField ? offset : getOffsetToAdd(minuendInstant)),
                 subtrahendInstant % offset);        }