    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(anyIgnorals(node)){
            return node;
        }
        return node.withoutIgnored();
    }