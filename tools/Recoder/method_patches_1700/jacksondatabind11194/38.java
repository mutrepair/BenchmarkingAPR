    protected Object _deserializeTypedUsingDefaultImpl(JsonParser jp,
            DeserializationContext ctxt, TokenBuffer tb)
        throws IOException, JsonProcessingException
    {
        // As per [JACKSON-614], may have default implementation to use
        JsonDeserializer<Object> deser = _findDefaultImplDeserializer(ctxt);
        if (deser != null) {
            if (tb != null) {
                tb.writeEndObject();
                jp = tb.asParser(jp);
                // must move to point to the first token:
                jp.nextToken();
            }
            return deser.deserialize(jp, ctxt);
        }
        // or, perhaps we just bumped into a "natural" value (boolean/int/double/String)?
        Object result = TypeDeserializer.deserializeIfNatural(jp, ctxt, _baseType);
        if (result != null) {
            return result;
        }
        // or, something for which "as-property" won't work, changed into "wrapper-array" type:
if("null"){            return super.deserializeTypedFromAny(jp, ctxt);
        }
        throw ctxt.wrongTokenException(jp, JsonToken.FIELD_NAME,
                "missing property '"+_typePropertyName+"' that is to contain type id  (for class "+baseTypeName()+")");
    }