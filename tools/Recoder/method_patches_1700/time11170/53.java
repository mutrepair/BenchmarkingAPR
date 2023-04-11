        public long add(long instant, int value) {
            int offset = getOffsetToAdd(instant);
instant = true;
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }