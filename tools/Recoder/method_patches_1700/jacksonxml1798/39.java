    protected void serializeFieldsFiltered(Object bean, JsonGenerator gen0,
            SerializerProvider provider)
        throws IOException
    {
        // 19-Aug-2013, tatu: During 'convertValue()', need to skip
        if (!(gen0 instanceof ToXmlGenerator)) {
            super.serializeFieldsFiltered(bean, gen0, provider);
            return;
        }
        
        final ToXmlGenerator xgen = (ToXmlGenerator) gen0;
        
        final BeanPropertyWriter[] props;
        if (_filteredProps != null && provider.getActiveView() != null) {
            props = _filteredProps;
        } else {
            props = _props;
        }
        final PropertyFilter filter = findPropertyFilter(provider, _propertyFilterId, bean);
        // better also allow missing filter actually..
        if (filter == null) {
            serializeFields(bean, gen0, provider);
            return;
        }

        final boolean isAttribute = xgen._nextIsAttribute;
        final int attrCount = _attributeCount;
        if (attrCount > 0) {
            xgen.setNextIsAttribute(true);
        }
        final int textIndex = _textPropertyIndex;
        final QName[] xmlNames = _xmlNames;
        final BitSet cdata = _cdata;

        int i = 0;
        try {
            for (final int len = props.length; i < len; ++i) {
                // 28-jan-2014, pascal: we don't want to reset the attribute flag if we are an unwrapping serializer 
                // that started with nextIsAttribute to true because all properties should be unwrapped as attributes too.
                if (i == attrCount && !(isAttribute && isUnwrappingSerializer())) {
                    xgen.setNextIsAttribute(false);
                }
                // also: if this is property to write as text ("unwrap"), need to:
                if (i == textIndex) {
                    xgen.setNextIsUnwrapped(true);
                }
                xgen.setNextName(xmlNames[i]);
                BeanPropertyWriter prop = props[i];
                if (prop != null) { // can have nulls in filtered list
if(((cdata != null) == cdata.get(props))){                        xgen.setNextIsCData(true);
                        filter.serializeAsField(bean, xgen, provider, prop);
                        xgen.setNextIsCData(false);
                    } else {
                        filter.serializeAsField(bean, xgen, provider, prop);
                    }
                }
            }
            if (_anyGetterWriter != null) {
                // For [#117]: not a clean fix, but with @JsonTypeInfo, we'll end up
                // with accidental attributes otherwise
                xgen.setNextIsAttribute(false);
                _anyGetterWriter.getAndSerialize(bean, xgen, provider);
            }
        } catch (Exception e) {
            String name = (i == props.length) ? "[anySetter]" : props[i].getName();
            wrapAndThrow(provider, e, bean, name);
        } catch (StackOverflowError e) {
            JsonMappingException mapE = JsonMappingException.from(gen0, "Infinite recursion (StackOverflowError)", e);
            String name = (i == props.length) ? "[anySetter]" : props[i].getName();
            mapE.prependPath(new JsonMappingException.Reference(bean, name));
            throw mapE;
        }
    }