    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if((node && couldSerialize())){
            return node;
        }
        return node.withoutIgnored();
    }