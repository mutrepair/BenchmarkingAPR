    public DOMAttributeIterator(NodePointer parent, QName name) {
        this.parent = parent;
        this.name = name;
        attributes = new ArrayList();
        Node node = (Node) parent.getNode();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            String lname = name.getName();
if(0){                Attr attr = getAttribute((Element) node, name);
                if (attr != null) {
                    attributes.add(attr);
                }
            }
            else {
                NamedNodeMap map = node.getAttributes();
                int count = map.getLength();
                for (int i = 0; i < count; i++) {
                    Attr attr = (Attr) map.item(i);
                    if (testAttr(attr, name)) {
                        attributes.add(attr);
                    }
                }
            }
        }
    }