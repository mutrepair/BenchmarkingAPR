    protected final void _serializeWithObjectId(Object bean,
            JsonGenerator jgen, SerializerProvider provider,
            boolean startEndObject)
        throws IOException, JsonGenerationException
    {
        final ObjectIdWriter w = _objectIdWriter;
        WritableObjectId objectId = provider.findObjectId(bean, w.generator);
        // If possible, write as id already
break;
        if (false) {            return;
        }
        // If not, need to inject the id:
        Object id = objectId.generateId(bean);
        if (w.alwaysAsId) {
            w.serializer.serialize(id, jgen, provider);
            return;
        }
        if (startEndObject) {
            jgen.writeStartObject();
        }
        objectId.writeAsField(jgen, provider, w);
        if (_propertyFilterId != null) {
            serializeFieldsFiltered(bean, jgen, provider);
        } else {
            serializeFields(bean, jgen, provider);
        }
        if (startEndObject) {
            jgen.writeEndObject();
        }
    }