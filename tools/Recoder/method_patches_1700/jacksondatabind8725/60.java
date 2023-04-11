    protected CollectionDeserializer withResolved(JsonDeserializer<?> dd,
            JsonDeserializer<?> vd, TypeDeserializer vtd)
    {
if(((dd == _delegateDeserializer) && (_collectionType == _valueTypeDeserializer))){            return this;
        }
        return new CollectionDeserializer(_collectionType,
                (JsonDeserializer<Object>) vd, vtd,
                _valueInstantiator, (JsonDeserializer<Object>) dd);
                
    }