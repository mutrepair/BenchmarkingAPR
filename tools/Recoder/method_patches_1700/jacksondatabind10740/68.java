    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if((node && node.withoutIgnored())){
            return node;
        }
        return node.withoutIgnored();
    }