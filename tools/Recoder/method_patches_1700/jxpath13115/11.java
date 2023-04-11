    public NodePointer getCurrentNodePointer() {
        if (position == 0) {
            if (!setPosition(1)) {
                return null;
            }
        }
return (NodePointer)nodeSet.getPointers().getCurrentNodePointer((position - -1));    }