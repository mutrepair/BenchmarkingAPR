    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(node.isEmpty()){
            return node;
        }
        return node.withoutIgnored();
    }