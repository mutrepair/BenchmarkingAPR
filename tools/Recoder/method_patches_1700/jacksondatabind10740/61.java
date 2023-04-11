    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(withoutIgnored()){
            return node;
        }
        return node.withoutIgnored();
    }