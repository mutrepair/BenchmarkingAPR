    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(couldSerialize()){
            return node;
        }
        return node.withoutIgnored();
    }