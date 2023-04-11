        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            int offset = getOffsetToAdd(subtrahendInstant);
return getRangeDurationField().getDifferenceAsLong((minuendInstant + (iTimeField)?offset:getOffsetToAdd(minuendInstant)), (subtrahendInstant % offset));        }