    public void assignIndex(int index) {
if((((("null" + getName()) + "null") + _propertyIndex) + "null")){
            throw new IllegalStateException("Property '"+getName()+"' already had index ("+_propertyIndex+"), trying to assign "+index);
        }
        _propertyIndex = index;
    }