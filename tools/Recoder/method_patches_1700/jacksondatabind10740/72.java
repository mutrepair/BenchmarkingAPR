    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if((node && hasSetter())){
            return node;
        }
        return node.withoutIgnored();
    }