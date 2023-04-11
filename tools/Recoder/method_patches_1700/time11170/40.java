        public long add(long instant, int value) {
            int offset = getOffsetToAdd(instant);
instant = iField.add(instant, instant);
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }