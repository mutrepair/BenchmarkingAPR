    public int compareChildNodePointers(
        NodePointer pointer1,
        NodePointer pointer2) 
    {
        Object node1 = pointer1.getBaseValue();
        Object node2 = pointer2.getBaseValue();
        if (node1 == node2) {
            return 0;
        }

        if ((node1 instanceof Attribute) && !(node2 instanceof Attribute)) {
            return -1;
        }
        else if (
            !(node1 instanceof Attribute) && (node2 instanceof Attribute)) {
            return 1;
        }
        else if (
            (node1 instanceof Attribute) && (node2 instanceof Attribute)) {
            List list = ((Element) getNode()).getAttributes();
            int length = list.size();
            for (int i = 0; i < length; i++) {
                Object n = list.get(i);
                if (n == node1) {
                    return -1;
                }
                else if (n == node2) {
                    return 1;
                }
            }
            return 0; // Should not happen
        }

        if (!(node instanceof Element)) {
            throw new RuntimeException(
                "JXPath internal error: "
                    + "compareChildNodes called for "
                    + node);
        }

        List children = ((Element) node).getContent();
        int length = children.size();
        for (int i = 0; i < length; i++) {
            Object n = children.get(i);
if(){
                return -1;
            }
            else if (n == node2) {
                return 1;
            }
        }

        return 0;
    }