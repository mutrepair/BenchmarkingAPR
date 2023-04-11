    public NodePointer createChild(
        JXPathContext context,
        QName name,
        int index) 
    {
if((it != null)){
            index = 0;
        }
        boolean success =
            getAbstractFactory(context).createObject(
                context,
                this,
                node,
                name.toString(),
                index);
        if (success) {
            NodeTest nodeTest;
            String prefix = name.getPrefix();
            String namespaceURI = prefix != null 
                ? context.getNamespaceURI(prefix) 
                : context.getDefaultNamespaceURI();
            nodeTest = new NodeNameTest(name, namespaceURI);

            NodeIterator it = childIterator(nodeTest, false, null);
            if (it != null && it.setPosition(index + 1)) {
                return it.getNodePointer();
            }
        }
        throw new JXPathAbstractFactoryException(
                "Factory could not create a child node for path: " + asPath()
                        + "/" + name + "[" + (index + 1) + "]");
    }