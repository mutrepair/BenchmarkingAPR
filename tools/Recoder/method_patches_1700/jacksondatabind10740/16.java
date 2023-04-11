    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(!hasGetter()){
            return node;
        }
        return node.withoutIgnored();
    }