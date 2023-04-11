        public long add(long instant, int value) {
            int offset = getOffsetToAdd(instant);
instant = iField.getOffsetFromLocalToSubtract((instant * offset), value);
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }