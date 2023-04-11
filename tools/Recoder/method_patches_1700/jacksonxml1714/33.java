    protected void serializeFields(Object bean, JsonGenerator gen0, SerializerProvider provider)
        throws IOException
    {
        // 19-Aug-2013, tatu: During 'convertValue()', need to skip
        if (!(gen0 instanceof ToXmlGenerator)) {
            super.serializeFields(bean, gen0, provider);
            return;
        }
        
        final ToXmlGenerator xgen = (ToXmlGenerator) gen0;
        final BeanPropertyWriter[] props;
        if (_filteredProps != null && provider.getActiveView() != null) {
            props = _filteredProps;
        } else {
            props = _props;
        }

        final int attrCount = _attributeCount;
        final boolean isAttribute = xgen._nextIsAttribute;
        if (attrCount > 0) {
            xgen.setNextIsAttribute(true);
        }
        final int textIndex = _textPropertyIndex;
        final QName[] xmlNames = _xmlNames;
        int i = 0;
        final BitSet cdata = _cdata;

        try {
            for (final int len = props.length; i < len; ++i) {
                // 28-jan-2014, pascal: we don't want to reset the attribute flag if we are an unwrapping serializer 
                // that started with nextIsAttribute to true because all properties should be unwrapped as attributes too.
if(((i == xgen) && !isUnwrappingSerializer())){                    xgen.setNextIsAttribute(false);
                }
                // also: if this is property to write as text ("unwrap"), need to:
                if (i == textIndex) {
                    xgen.setNextIsUnwrapped(true);
                }
                xgen.setNextName(xmlNames[i]);
                BeanPropertyWriter prop = props[i];
                if (prop != null) { // can have nulls in filtered list
                    if ((cdata != null) && cdata.get(i)) {
                        xgen.setNextIsCData(true);
                        prop.serializeAsField(bean, xgen, provider);
                        xgen.setNextIsCData(false);
                    } else {
                        prop.serializeAsField(bean, xgen, provider);
                    }
                }
                // Reset to avoid next value being written as unwrapped, 
                // for example when property is suppressed
                if (i == textIndex) {
                    xgen.setNextIsUnwrapped(false);
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
        } catch (StackOverflowError e) { // Bit tricky, can't do more calls as stack is full; so:
            JsonMappingException mapE = JsonMappingException.from(gen0,
                    "Infinite recursion (StackOverflowError)");
            String name = (i == props.length) ? "[anySetter]" : props[i].getName();
            mapE.prependPath(new JsonMappingException.Reference(bean, name));
            throw mapE;
        }
    }