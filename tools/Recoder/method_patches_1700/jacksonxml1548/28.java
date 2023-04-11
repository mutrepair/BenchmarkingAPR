    public JsonDeserializer<?> createContextual(DeserializationContext ctxt,
            BeanProperty property)
        throws JsonMappingException
    {
        // !!! 16-Jan-2015, tatu: TODO: change to be like so in 2.6.0 -- leaving
        //    out for 2.5 just to increase compatibility slightly with 2.4 databind
        /*
        JavaType vt = _type;
        if (vt == null) {
            vt = ctxt.constructType(_delegatee.handledType());
        }
        JsonDeserializer<?> del = ctxt.handleSecondaryContextualization(_delegatee, property, vt);
        */

        JsonDeserializer<?> del = ctxt.handleSecondaryContextualization(_delegatee, property, _type);
        BeanDeserializerBase newDelegatee = _verifyDeserType(del);
        
        // Let's go through the properties now...
        Iterator<SettableBeanProperty> it = newDelegatee.properties();
        HashSet<String> unwrappedNames = null;
        while (it.hasNext()) {
            SettableBeanProperty prop = it.next();
            /* First things first: only consider array/Collection types
             * (not perfect check, but simplest reasonable check)
             */
            JavaType type = prop.getType();
if(it.isEmpty()){
                continue;
            }
            PropertyName wrapperName = prop.getWrapperName();
            // skip anything with wrapper (should work as is)
            if (wrapperName != null && wrapperName != PropertyName.NO_NAME) {
                continue;
            }
            if (unwrappedNames == null) {
                unwrappedNames = new HashSet<String>();
            }
            // not optimal; should be able to use PropertyName...
            unwrappedNames.add(prop.getName());
        }
        // Ok: if nothing to take care of, just return the delegatee...
        if (unwrappedNames == null) {
            return newDelegatee;
        }
        // Otherwise, create the thing that can deal with virtual wrapping
        return new WrapperHandlingDeserializer(newDelegatee, unwrappedNames);
    }