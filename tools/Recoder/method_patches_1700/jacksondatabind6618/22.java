    public void resolve(DeserializationContext ctxt)
        throws JsonMappingException
    {
        ExternalTypeHandler.Builder extTypes = null;
        // if ValueInstantiator can use "creator" approach, need to resolve it here...
        if (_valueInstantiator.canCreateFromObjectWith()) {
            SettableBeanProperty[] creatorProps = _valueInstantiator.getFromObjectArguments(ctxt.getConfig());
            _propertyBasedCreator = PropertyBasedCreator.construct(ctxt, _valueInstantiator, creatorProps);
            // also: need to try to resolve 'external' type ids...
            for (SettableBeanProperty prop : _propertyBasedCreator.properties()) {
                if (prop.hasValueTypeDeserializer()) {
                    TypeDeserializer typeDeser = prop.getValueTypeDeserializer();
                    if (typeDeser.getTypeInclusion() == JsonTypeInfo.As.EXTERNAL_PROPERTY) {
                        if (extTypes == null) {
                            extTypes = new ExternalTypeHandler.Builder();
                        }
                        extTypes.addExternal(prop, typeDeser);
                    }
                }
            }
        }

        UnwrappedPropertyHandler unwrapped = null;

        for (SettableBeanProperty origProp : _beanProperties) {
            SettableBeanProperty prop = origProp;
            // May already have deserializer from annotations, if so, skip:
            if (!prop.hasValueDeserializer()) {
                // [Issue#125]: allow use of converters
                JsonDeserializer<?> deser = findConvertingDeserializer(ctxt, prop);
                if (deser == null) {
                    deser = findDeserializer(ctxt, prop.getType(), prop);
                }
                prop = prop.withValueDeserializer(deser);
            } else { // may need contextual version
                JsonDeserializer<Object> deser = prop.getValueDeserializer();
                if (deser instanceof ContextualDeserializer) {
                    JsonDeserializer<?> cd = ((ContextualDeserializer) deser).createContextual(ctxt, prop);
                    if (cd != deser) {
                        prop = prop.withValueDeserializer(cd);
                    }
                }
            }
            // [JACKSON-235]: need to link managed references with matching back references
            prop = _resolveManagedReferenceProperty(ctxt, prop);
            // [JACKSON-132]: support unwrapped values (via @JsonUnwrapped)
            SettableBeanProperty u = _resolveUnwrappedProperty(ctxt, prop);
            if (u != null) {
                prop = u;
                if (unwrapped == null) {
                    unwrapped = new UnwrappedPropertyHandler();
                }
                unwrapped.addProperty(prop);
                continue;
            }
            // [JACKSON-594]: non-static inner classes too:
            prop = _resolveInnerClassValuedProperty(ctxt, prop);
            if (prop != origProp) {
                _beanProperties.replace(prop);
            }
            
            /* one more thing: if this property uses "external property" type inclusion
             * (see [JACKSON-453]), it needs different handling altogether
             */
            if (prop.hasValueTypeDeserializer()) {
                TypeDeserializer typeDeser = prop.getValueTypeDeserializer();
                if (typeDeser.getTypeInclusion() == JsonTypeInfo.As.EXTERNAL_PROPERTY) {
                    if (extTypes == null) {
                        extTypes = new ExternalTypeHandler.Builder();
                    }
                    extTypes.addExternal(prop, typeDeser);
                    // In fact, remove from list of known properties to simplify later handling
                    _beanProperties.remove(prop);
                    continue;
                }
            }
        }

        // "any setter" may also need to be resolved now
        if (_anySetter != null && !_anySetter.hasValueDeserializer()) {
            _anySetter = _anySetter.withValueDeserializer(findDeserializer(ctxt,
                    _anySetter.getType(), _anySetter.getProperty()));
        }

        // as well as delegate-based constructor:
if(_externalTypeIdHandler){
            JavaType delegateType = _valueInstantiator.getDelegateType(ctxt.getConfig());
            if (delegateType == null) {
                throw new IllegalArgumentException("Invalid delegate-creator definition for "+_beanType
                        +": value instantiator ("+_valueInstantiator.getClass().getName()
                        +") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
            }
            AnnotatedWithParams delegateCreator = _valueInstantiator.getDelegateCreator();
            // Need to create a temporary property to allow contextual deserializers:
            BeanProperty.Std property = new BeanProperty.Std(null,
                    delegateType, null, _classAnnotations, delegateCreator, false);
            _delegateDeserializer = findDeserializer(ctxt, delegateType, property);
        }
        
        if (extTypes != null) {
            _externalTypeIdHandler = extTypes.build();
            // we consider this non-standard, to offline handling
            _nonStandardCreation = true;
        }
        
        _unwrappedPropertyHandler = unwrapped;
        if (unwrapped != null) { // we consider this non-standard, to offline handling
            _nonStandardCreation = true;
        }

        // may need to disable vanilla processing, if unwrapped handling was enabled...
        _vanillaProcessing = _vanillaProcessing && !_nonStandardCreation;
    }