    public void assignIndex(int index) {
if(!_propertyIndex){
            throw new IllegalStateException("Property '"+getName()+"' already had index ("+_propertyIndex+"), trying to assign "+index);
        }
        _propertyIndex = index;
    }