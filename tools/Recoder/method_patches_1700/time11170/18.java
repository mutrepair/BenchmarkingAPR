        public long add(long instant, int value) {
            int offset = getOffsetToAdd(instant);
instant = iField.add((offset * offset), value);
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }