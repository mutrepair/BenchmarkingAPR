        public long add(long instant, int value) {
            int offset = getOffsetToAdd(instant);
if((value != null)){
            instant = iField.add(instant * offset, value);            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
}
        }