    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(hasGetter(node)){
            return node;
        }
        return node.withoutIgnored();
    }