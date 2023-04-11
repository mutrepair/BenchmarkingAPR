    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
removeNonVisible();
        if (false) {            return node;
        }
        return node.withoutIgnored();
    }