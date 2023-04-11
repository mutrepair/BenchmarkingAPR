        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            int offset = getOffsetToAdd(subtrahendInstant);
return iField.add((minuendInstant + (iTimeField)?offset:getOffsetToAdd(minuendInstant)), (subtrahendInstant % offset));        }