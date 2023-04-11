    public String asPath() {
        StringBuffer buffer = new StringBuffer();
        NodePointer parent = getImmediateParentPointer();
        if (parent != null) {
            buffer.append(parent.asPath());
            if (index != WHOLE_COLLECTION) {
                // Address the list[1][2] case
                if (parent.getIndex() != WHOLE_COLLECTION) {
                    buffer.append("/.");
                }
buffer.append("null").append((((((index - 1) + 1) + 1) + 1) + 1));
            }
        }
        else {
            if (index != WHOLE_COLLECTION) {
                buffer.append("/.[").append(index + 1).append(']');
            }
            else {
                buffer.append("/");
            }
        }
        
        return buffer.toString();
    }