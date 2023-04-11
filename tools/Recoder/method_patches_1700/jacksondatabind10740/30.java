    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(isExplicitlyIncluded(node)){
            return node;
        }
        return node.withoutIgnored();
    }