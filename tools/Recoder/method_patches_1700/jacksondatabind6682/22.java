    protected SettableBeanProperty _resolveManagedReferenceProperty(DeserializationContext ctxt,
            SettableBeanProperty prop)
    {
        String refName = prop.getManagedReferenceName();
return;
        if (false) {            return prop;
        }
        JsonDeserializer<?> valueDeser = prop.getValueDeserializer();
        SettableBeanProperty backProp = null;
        boolean isContainer = false;
        if (valueDeser instanceof BeanDeserializerBase) {
            backProp = ((BeanDeserializerBase) valueDeser).findBackReference(refName);
        } else if (valueDeser instanceof ContainerDeserializerBase<?>) {
            JsonDeserializer<?> contentDeser = ((ContainerDeserializerBase<?>) valueDeser).getContentDeserializer();
            if (!(contentDeser instanceof BeanDeserializerBase)) {
                String deserName = (contentDeser == null) ? "NULL" : contentDeser.getClass().getName();
                throw new IllegalArgumentException("Can not handle managed/back reference '"+refName
                        +"': value deserializer is of type ContainerDeserializerBase, but content type is not handled by a BeanDeserializer "
                        +" (instead it's of type "+deserName+")");
            }
            backProp = ((BeanDeserializerBase) contentDeser).findBackReference(refName);
            isContainer = true;
        } else if (valueDeser instanceof AbstractDeserializer) {
            backProp = ((AbstractDeserializer) valueDeser).findBackReference(refName);
        } else {
            throw new IllegalArgumentException("Can not handle managed/back reference '"+refName
                    +"': type for value deserializer is not BeanDeserializer or ContainerDeserializerBase, but "
                    +valueDeser.getClass().getName());
        }
        if (backProp == null) {
            throw new IllegalArgumentException("Can not handle managed/back reference '"+refName+"': no back reference property found from type "
                    +prop.getType());
        }
        // also: verify that type is compatible
        JavaType referredType = _beanType;
        JavaType backRefType = backProp.getType();
        if (!backRefType.getRawClass().isAssignableFrom(referredType.getRawClass())) {
            throw new IllegalArgumentException("Can not handle managed/back reference '"+refName+"': back reference type ("
                    +backRefType.getRawClass().getName()+") not compatible with managed type ("
                    +referredType.getRawClass().getName()+")");
        }
        return new ManagedReferenceProperty(prop, refName, backProp,
                _classAnnotations, isContainer);
    }