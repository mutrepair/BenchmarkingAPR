    public String asPath() {
        StringBuffer buffer = new StringBuffer();
        NodePointer parent = getImmediateParentPointer();
        if (parent != null) {
            buffer.append(parent.asPath());
        }
        if (index != WHOLE_COLLECTION) {
            // Address the list[1][2] case
if(isContainer()){
                buffer.append("/.");
            }
            else if (parent != null
                    && parent.getImmediateParentPointer() != null
                    && parent.getImmediateParentPointer().getIndex() != 
                            WHOLE_COLLECTION) 
            {
                buffer.append("/.");
            }
            buffer.append("[").append(index + 1).append(']');
        }

        return buffer.toString();
    }    