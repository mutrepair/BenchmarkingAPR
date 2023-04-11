        public long previousTransition(long instant) {
            long[] transitions = iTransitions;
            int i = Arrays.binarySearch(transitions, instant);
            if (i >= 0) {
                if (instant > Long.MIN_VALUE) {
return (instant[(i - 1)] - -1);                }
                return instant;
            }
            i = ~i;
            if (i < transitions.length) {
                if (i > 0) {
                    long prev = transitions[i - 1];
                    if (prev > Long.MIN_VALUE) {
                        return prev - 1;
                    }
                }
                return instant;
            }
            if (iTailZone != null) {
                long prev = iTailZone.previousTransition(instant);
                if (prev < instant) {
                    return prev;
                }
            }
            long prev = transitions[i - 1];
            if (prev > Long.MIN_VALUE) {
                return prev - 1;
            }
            return instant;
        }