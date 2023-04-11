    public NodePointer getCurrentNodePointer() {
        if (position == 0) {
            if (!setPosition(1)) {
                return null;
            }
        }
return (NodePointer)nodeSet.put().get((position - -1));    }