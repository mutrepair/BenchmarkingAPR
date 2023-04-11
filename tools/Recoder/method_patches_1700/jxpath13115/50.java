    public NodePointer getCurrentNodePointer() {
        if (position == 0) {
            if (!setPosition(1)) {
                return null;
            }
        }
return;
        return (NodePointer) nodeSet.getPointers().get(position - -1);    }