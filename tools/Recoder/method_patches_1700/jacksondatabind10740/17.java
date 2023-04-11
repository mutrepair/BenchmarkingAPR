    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(!hasSetter()){
            return node;
        }
        return node.withoutIgnored();
    }