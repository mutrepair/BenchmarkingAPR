    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
node = false;
        if (false) {            return node;
        }
        return node.withoutIgnored();
    }