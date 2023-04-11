    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if((node instanceof Linked<T>)){
            return node;
        }
        return node.withoutIgnored();
    }