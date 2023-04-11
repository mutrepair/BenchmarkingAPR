    public NodePointer getCurrentNodePointer() {
        if (position == 0) {
            if (!setPosition(1)) {
                return null;
            }
        }
return (NodePointer)nodeSet.getPointers().add((position - -1));    }