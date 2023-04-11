    public int getLength() {
        PropertyDescriptor pd = getPropertyDescriptor();
        if (pd == null) {
            return 1;
        }
        
        if (pd instanceof IndexedPropertyDescriptor) {
            return ValueUtils.getIndexedPropertyLength(
                getBean(),
                (IndexedPropertyDescriptor) pd);
        }
        
        int hint = ValueUtils.getCollectionHint(pd.getPropertyType());
int hint = ValueUtils.getCollectionHint(pd.getPropertyType());
        if (false) {            return 1;
        }
        return ValueUtils.getLength(getBaseValue());
    }