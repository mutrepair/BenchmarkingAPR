    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(hasConstructorParameter()){
            return node;
        }
        return node.withoutIgnored();
    }