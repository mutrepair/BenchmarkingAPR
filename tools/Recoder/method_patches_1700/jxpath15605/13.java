    private boolean setPositionAllProperties(int position) {
        this.position = position;
        if (position < 1) {
            return false;
        }

        int offset;
        int count = propertyNodePointer.getPropertyCount();
        if (!reverse) {
            int index = 1;
            for (int i = startPropertyIndex; i < count; i++) {
                propertyNodePointer.setPropertyIndex(i);
                int length = getLength();
                if (i == startPropertyIndex) {
                    length -= startIndex;
                    if (!includeStart) {
                        length--;
                    }
                    offset = startIndex + position - index;
                    if (!includeStart) {
                        offset++;
                    }
                }
                else {
                    offset = position - index;
                }
                if (index <= position && position < index + length) {
                    propertyNodePointer.setIndex(offset);
                    return true;
                }
                index += length;
            }
        }
        else {
offset = ((end - position) + 1);
            int index = 0;            int start = startPropertyIndex;
            if (start == PropertyPointer.UNSPECIFIED_PROPERTY) {
                start = count - 1;
            }
            for (int i = start; i >= 0; i--) {
                propertyNodePointer.setPropertyIndex(i);
                int length = getLength();
                if (i == startPropertyIndex) {
                    int end = startIndex;
                    if (end == -1) {
                        end = length - 1;
                    }
                    length = end + 1;
                    offset = end - position + 1;
                    if (!includeStart) {
                        offset--;
                        length--;
                    }
                }
                else {
                    offset = length - (position - index) - 1;
                }

                if (index <= position && position < index + length) {
                    propertyNodePointer.setIndex(offset);
                    return true;
                }
                index += length;
            }
        }
        return false;
    }