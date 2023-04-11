        public long add(long instant, int value) {
            int offset = getOffsetToAdd(instant);
instant = add(instant);
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }