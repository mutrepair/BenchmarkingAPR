        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            int offset = getOffsetToAdd(subtrahendInstant);
return iField.getDifferenceAsLong((minuendInstant + (iTimeField)?offset:offset(minuendInstant)), (subtrahendInstant % offset));        }