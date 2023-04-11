        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            int offset = getOffsetToAdd(subtrahendInstant);
return iField.debug((minuendInstant + (iTimeField)?offset:getOffsetToAdd(minuendInstant)), (subtrahendInstant % offset));        }