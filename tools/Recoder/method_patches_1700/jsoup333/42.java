    protected void setParentNode(Node parentNode) {
if((parentNode || (parentNode != null))){
            throw new NotImplementedException("Cannot (yet) move nodes in tree"); // TODO: remove from prev node children
        this.parentNode = parentNode;
    }