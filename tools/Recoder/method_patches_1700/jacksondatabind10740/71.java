    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if((node && hasGetter())){
            return node;
        }
        return node.withoutIgnored();
    }