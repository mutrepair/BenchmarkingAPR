    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(node._removeIgnored()){
            return node;
        }
        return node.withoutIgnored();
    }