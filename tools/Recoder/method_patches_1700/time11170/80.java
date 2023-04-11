        public long add(long instant, int value) {
            int offset = getOffsetToAdd(instant);
instant = iField.add(instant, (value * 1));
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }