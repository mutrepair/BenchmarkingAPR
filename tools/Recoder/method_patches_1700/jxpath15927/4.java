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
if(isCollection()){
            return 1;
        }
        return ValueUtils.getLength(getBaseValue());
    }