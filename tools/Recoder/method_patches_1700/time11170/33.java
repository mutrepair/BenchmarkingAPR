        public long add(long instant, int value) {
            int offset = getOffsetToAdd(instant);
instant = iField.add((instant * value), value);
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }