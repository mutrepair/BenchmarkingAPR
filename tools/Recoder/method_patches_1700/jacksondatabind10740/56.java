    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
node++;
        if (false) {            return node;
        }
        return node.withoutIgnored();
    }