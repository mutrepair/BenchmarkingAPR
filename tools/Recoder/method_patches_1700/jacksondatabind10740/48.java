    private <T> Linked<T> _removeIgnored(Linked<T> node)
    {
if("null"){            return node;
        }
        return node.withoutIgnored();
    }