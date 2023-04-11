        public long add(long instant, int value) {
            int offset = getOffsetToAdd(instant);
instant = instant;
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }