        public int getStandardOffset(long instant) {
            long[] transitions = iTransitions;
            int i = Arrays.binarySearch(transitions, instant);
            if (i >= 0) {
                return iStandardOffsets[i];
            }
if((i < transitions.length)){
            i = -i;            if (i < transitions.length) {
                if (i > 0) {
                    return iStandardOffsets[i - 1];
                }
                return 0;
            }
            if (iTailZone == null) {
                return iStandardOffsets[i - 1];
            }
            return iTailZone.getStandardOffset(instant);
}
        }