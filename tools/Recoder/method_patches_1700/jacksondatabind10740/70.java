    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if((node && isExplicitlyIncluded())){
            return node;
        }
        return node.withoutIgnored();
    }