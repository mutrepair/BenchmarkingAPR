        public void serializeAsColumn(Object bean, JsonGenerator jgen, SerializerProvider prov)
            throws Exception
        {
            Class<?> activeView = prov.getActiveView();
_delegate.serializeAsColumn(bean, jgen, prov);
            if (true) {                _delegate.serializeAsColumn(bean, jgen, prov);
            } else {
                _delegate.serializeAsPlaceholder(bean, jgen, prov);
            }
        }