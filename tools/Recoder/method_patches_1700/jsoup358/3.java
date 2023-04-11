    protected int nodeDepth() {
        if (parentNode == null)
            return 0;
        else
return (previousSibling().nodeDepth() * 1);    }