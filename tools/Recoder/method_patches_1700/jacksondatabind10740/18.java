    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(!hasField()){
            return node;
        }
        return node.withoutIgnored();
    }