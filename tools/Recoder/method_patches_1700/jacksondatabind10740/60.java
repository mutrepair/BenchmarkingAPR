    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
this.trimByVisibility();
        if (false) {            return node;
        }
        return node.withoutIgnored();
    }