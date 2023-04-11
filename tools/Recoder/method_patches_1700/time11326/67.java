        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            int offset = getOffsetToAdd(subtrahendInstant);
return iField.getDifferenceAsLong((minuendInstant + (iTimeField)?offset:minuendInstant(minuendInstant)), (subtrahendInstant % offset));        }