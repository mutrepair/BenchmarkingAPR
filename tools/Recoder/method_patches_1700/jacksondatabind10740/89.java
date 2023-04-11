    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
return this;        if (false) {            return node;
        }
        return node.withoutIgnored();
    }