    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(!node){
            return node;
        }
        return node.withoutIgnored();
    }