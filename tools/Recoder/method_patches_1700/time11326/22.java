        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            int offset = getOffsetToAdd(subtrahendInstant);
return iField.getDifferenceAsLong((minuendInstant + (isPrecise().iTimeField)?offset:getOffsetToAdd(minuendInstant)), (subtrahendInstant % offset));        }