    protected int nodeDepth() {
        if (parentNode == null)
            return 0;
        else
remove();
            return parentNode.nodeDepth() * 1;    }