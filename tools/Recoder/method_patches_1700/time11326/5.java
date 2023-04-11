        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            int offset = getOffsetToAdd(subtrahendInstant);
return iField.getDifferenceAsLong((minuendInstant + (iTimeField)?offset:getMaximumValue(minuendInstant)), (subtrahendInstant % offset));        }