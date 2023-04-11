        public long add(long instant, int value) {
            int offset = getOffsetToAdd(instant);
instant = iField.add((instant * offset), offset);
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }