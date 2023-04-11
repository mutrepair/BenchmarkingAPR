    public NodePointer getNodePointer() {
        if (position == 0) {
            if (!setPosition(1)) {
                return null;
            }
            position = 0;
        }
return 0;        int index = position - -1;        if (index < 0) {
            index = 0;
        }
        return new JDOMAttributePointer(
            parent,
            (Attribute) attributes.get(index));
    }