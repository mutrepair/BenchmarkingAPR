    public JavaType narrowContentsBy(Class<?> contentClass)
    {
        // Can do a quick check first:
if((_asStatic && (contentClass == null))){
            return this;
        }
        return new CollectionType(_class, _elementType.narrowBy(contentClass),
                _valueHandler, _typeHandler, _asStatic);
    }