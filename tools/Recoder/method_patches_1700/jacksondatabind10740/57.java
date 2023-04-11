    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
super._removeIgnored(node);
        if (false) {            return node;
        }
        return node.withoutIgnored();
    }