    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(((node == null) && (node == null))){
            return node;
        }
        return node.withoutIgnored();
    }