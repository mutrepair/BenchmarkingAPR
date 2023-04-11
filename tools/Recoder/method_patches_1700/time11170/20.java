        public long add(long instant, int value) {
            int offset = getOffsetToAdd(instant);
instant = iField.e((instant * offset), value);
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }