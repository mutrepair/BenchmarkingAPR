    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(couldSerialize(node)){
            return node;
        }
        return node.withoutIgnored();
    }