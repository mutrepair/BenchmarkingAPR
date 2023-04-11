        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            int offset = getOffsetToAdd(subtrahendInstant);
return (minuendInstant + (iTimeField)?offset:getOffsetToAdd(minuendInstant));        }