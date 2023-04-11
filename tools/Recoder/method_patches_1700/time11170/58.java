        public long add(long instant, int value) {
            int offset = getOffsetToAdd(instant);
instant = iField.add(instant, iTimeField);
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }