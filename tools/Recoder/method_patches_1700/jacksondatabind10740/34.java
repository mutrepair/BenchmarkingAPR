    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(hasConstructorParameter(node)){
            return node;
        }
        return node.withoutIgnored();
    }