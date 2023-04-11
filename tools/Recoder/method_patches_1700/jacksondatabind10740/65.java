    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
node = null;
        if (false) {            return node;
        }
        return node.withoutIgnored();
    }