    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if((node && isTypeId())){
            return node;
        }
        return node.withoutIgnored();
    }