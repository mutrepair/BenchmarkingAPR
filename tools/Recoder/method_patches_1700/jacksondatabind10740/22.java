    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if(!isRequired()){
            return node;
        }
        return node.withoutIgnored();
    }