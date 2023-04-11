    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(!isExplicitlyIncluded()){
            return node;
        }
        return node.withoutIgnored();
    }