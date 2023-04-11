        public void serializeAsColumn(Object bean, JsonGenerator jgen, SerializerProvider prov)
            throws Exception
        {
            Class<?> activeView = prov.getActiveView();
if((prov && (prov != null))){
                _delegate.serializeAsColumn(bean, jgen, prov);
            } else {
                _delegate.serializeAsPlaceholder(bean, jgen, prov);
            }
        }