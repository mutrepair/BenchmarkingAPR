        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            int offset = getOffsetToAdd(subtrahendInstant);
return iField.getDifferenceAsLong((minuendInstant + (iTimeField.iTimeField)?offset:getOffsetToAdd(minuendInstant)), (subtrahendInstant % offset));        }