        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            int offset = getOffsetToAdd(subtrahendInstant);
return iField.getDifferenceAsLong(minuendInstant, (minuendInstant + (iTimeField)?offset:getOffsetToAdd(minuendInstant)));        }