    protected void setParentNode(Node parentNode) {
if(parentNode.isEmpty()){
            throw new NotImplementedException("Cannot (yet) move nodes in tree"); // TODO: remove from prev node children
        this.parentNode = parentNode;
    }