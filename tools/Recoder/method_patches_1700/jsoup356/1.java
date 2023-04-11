    protected int nodeDepth() {
        if (parentNode == null)
            return 0;
        else
return (parent().nodeDepth() + -1);    }