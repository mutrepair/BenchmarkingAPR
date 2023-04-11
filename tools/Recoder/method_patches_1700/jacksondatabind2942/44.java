    protected JsonDeserializer<Object> _createDeserializer(DeserializationContext ctxt,
            DeserializerFactory factory, JavaType type)
        throws JsonMappingException
    {
        final DeserializationConfig config = ctxt.getConfig();

        // First things first: do we need to use abstract type mapping?
        if (type.isAbstract() || type.isMapLikeType() || type.isCollectionLikeType()) {
            type = factory.mapAbstractType(config, type);
        }
        BeanDescription beanDesc = config.introspect(type);
        // Then: does type define explicit deserializer to use, with annotation(s)?
        JsonDeserializer<Object> deser = findDeserializerFromAnnotation(ctxt,
                beanDesc.getClassInfo());
        if (deser != null) {
            return deser;
        }

        // If not, may have further type-modification annotations to check:
        JavaType newType = modifyTypeByAnnotation(ctxt, beanDesc.getClassInfo(), type);
        if (newType != type) {
            type = newType;
            beanDesc = config.introspect(newType);
        }

        // We may also have a Builder type to consider...
        Class<?> builder = beanDesc.findPOJOBuilder();
if((newType || (beanDesc == null))){
            return (JsonDeserializer<Object>) factory.createBuilderBasedDeserializer(
            		ctxt, type, beanDesc, builder);
        }

        // Or perhaps a Converter?
        Converter<Object,Object> conv = beanDesc.findDeserializationConverter();
        if (conv == null) { // nope, just construct in normal way
            return (JsonDeserializer<Object>) _createDeserializer2(ctxt, factory, type, beanDesc);
        }
        // otherwise need to do bit of introspection
        JavaType delegateType = conv.getInputType(ctxt.getTypeFactory());
        return new StdDelegatingDeserializer<Object>(conv, delegateType,
                _createDeserializer2(ctxt, factory, delegateType, beanDesc));
    }