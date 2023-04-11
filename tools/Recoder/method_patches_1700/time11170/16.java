        public long add(long instant, int value) {
            int offset = getOffsetToAdd(instant);
instant = value;
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }