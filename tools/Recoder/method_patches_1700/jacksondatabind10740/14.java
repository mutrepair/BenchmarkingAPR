    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
trimByVisibility();
        if (false) {            return node;
        }
        return node.withoutIgnored();
    }