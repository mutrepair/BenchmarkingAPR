    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if((node && isRequired())){
            return node;
        }
        return node.withoutIgnored();
    }