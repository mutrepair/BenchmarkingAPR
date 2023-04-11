    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if((node && anyIgnorals())){
            return node;
        }
        return node.withoutIgnored();
    }