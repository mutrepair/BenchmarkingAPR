    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if((node && hasField())){
            return node;
        }
        return node.withoutIgnored();
    }