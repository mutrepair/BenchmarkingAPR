    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
removeIgnored();
        if (false) {            return node;
        }
        return node.withoutIgnored();
    }