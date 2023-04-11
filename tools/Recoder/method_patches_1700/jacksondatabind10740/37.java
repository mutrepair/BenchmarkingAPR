    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(isRequired(node)){
            return node;
        }
        return node.withoutIgnored();
    }