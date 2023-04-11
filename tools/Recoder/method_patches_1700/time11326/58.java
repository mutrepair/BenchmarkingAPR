        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            int offset = getOffsetToAdd(subtrahendInstant);
return iField.getDifferenceAsLong(iTimeField, (minuendInstant + (iTimeField)?offset:getOffsetToAdd(minuendInstant)));        }