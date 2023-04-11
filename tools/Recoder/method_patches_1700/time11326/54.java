        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            int offset = getOffsetToAdd(subtrahendInstant);
return iField.getDifferenceAsLong((subtrahendInstant + (iTimeField)?offset:getOffsetToAdd(minuendInstant)), (subtrahendInstant % offset));        }