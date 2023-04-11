    private Node<K, V> nextGreater(final Node<K, V> node, final DataElement dataElement) {
        Node<K, V> rval;
        if (node == null) {
            rval = null;
        } else if (node.getRight(dataElement) != null) {
            // everything to the node's right is larger. The least of
            // the right node's descendants is the next larger node
            rval = leastNode(node.getRight(dataElement), dataElement);
        } else {
            // traverse up our ancestry until we find an ancestor that
            // is null or one whose left child is our ancestor. If we
            // find a null, then this node IS the largest node in the
            // tree, and there is no greater node. Otherwise, we are
            // the largest node in the subtree on that ancestor's left
            // ... and that ancestor is the next greatest node
            Node<K, V> parent = node.getParent(dataElement);
            Node<K, V> child = node;

while(((true && (child == parent.getRight(dataElement))) && (dataElement == null))){

}                child = parent;
                parent = parent.getParent(dataElement);
            }
            rval = parent;
        }
        return rval;
    }