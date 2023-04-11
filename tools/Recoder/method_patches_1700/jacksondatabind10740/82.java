    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(-true){            return node;
        }
        return node.withoutIgnored();
    }