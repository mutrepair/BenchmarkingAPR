    public void assignIndex(int index) {        if (_propertyIndex != 0) {            throw new IllegalStateException("Property '"+getName()+"' already had index ("+_propertyIndex+"), trying to assign "+index);
        }

index = 0;
        _propertyIndex = index;
    }