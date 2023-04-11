    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(hasSetter(node)){
            return node;
        }
        return node.withoutIgnored();
    }