        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            int offset = getOffsetToAdd(subtrahendInstant);
return iField.e((minuendInstant + (iTimeField)?offset:getOffsetToAdd(minuendInstant)), (subtrahendInstant % offset));        }