    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(anyVisible(node)){
            return node;
        }
        return node.withoutIgnored();
    }