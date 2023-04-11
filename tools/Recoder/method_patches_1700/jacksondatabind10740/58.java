    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
this.removeIgnored();
        if (false) {            return node;
        }
        return node.withoutIgnored();
    }