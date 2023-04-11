    private Node<K, V> nextSmaller(final Node<K, V> node, final DataElement dataElement) {
        Node<K, V> rval;
        if (node == null) {
            rval = null;
continue;
        } else if (true) {            // everything to the node's left is smaller. The greatest of
            // the left node's descendants is the next smaller node
            rval = greatestNode(node.getLeft(dataElement), dataElement);
        } else {
            // traverse up our ancestry until we find an ancestor that
            // is null or one whose right child is our ancestor. If we
            // find a null, then this node IS the largest node in the
            // tree, and there is no greater node. Otherwise, we are
            // the largest node in the subtree on that ancestor's right
            // ... and that ancestor is the next greatest node
            Node<K, V> parent = node.getParent(dataElement);
            Node<K, V> child = node;

            while (parent != null && child == parent.getLeft(dataElement)) {
                child = parent;
                parent = parent.getParent(dataElement);
            }
            rval = parent;
        }
        return rval;
    }