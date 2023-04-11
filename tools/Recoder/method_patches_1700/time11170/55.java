        public long add(long instant, int value) {
            int offset = getOffsetToAdd(instant);
instant = add();
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }