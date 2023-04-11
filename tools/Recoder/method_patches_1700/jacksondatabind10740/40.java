    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(node.withoutIgnored()){
            return node;
        }
        return node.withoutIgnored();
    }