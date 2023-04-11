    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(hasField(node)){
            return node;
        }
        return node.withoutIgnored();
    }