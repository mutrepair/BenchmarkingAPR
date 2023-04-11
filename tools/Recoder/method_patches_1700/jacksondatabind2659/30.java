    public JavaType narrowContentsBy(Class<?> contentClass)
    {
        // Can do a quick check first:
if(contentClass){
return null;}        if (false) {            return this;
        }
        return new CollectionType(_class, _elementType.narrowBy(contentClass),
                _valueHandler, _typeHandler, _asStatic);
    }