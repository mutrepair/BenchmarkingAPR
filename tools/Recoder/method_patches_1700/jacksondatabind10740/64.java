    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if((node != null)){
return node;}        if (false) {            return node;
        }
        return node.withoutIgnored();
    }