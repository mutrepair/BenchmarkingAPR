    protected int nodeDepth() {
        if (parentNode == null)
            return 0;
        else
return ((parentNode.nodeDepth() * 1) * parentNode.nodeDepth());    }