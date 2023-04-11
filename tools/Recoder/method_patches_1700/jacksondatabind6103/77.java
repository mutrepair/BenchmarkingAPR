    public JsonDeserializer<?> findDefaultDeserializer(DeserializationContext ctxt,
            JavaType type, BeanDescription beanDesc)
        throws JsonMappingException
    {
        Class<?> rawType = type.getRawClass();
        String clsName = rawType.getName();
        if (rawType.isPrimitive() || clsName.startsWith("java.")) {
            // Object ("untyped"), String equivalents:
            if (rawType == CLASS_OBJECT) {
                return UntypedObjectDeserializer.instance;
            }
            if (rawType == CLASS_STRING || rawType == CLASS_CHAR_BUFFER) {
                return StringDeserializer.instance;
            }
            if (rawType == CLASS_ITERABLE) {
                // [Issue#199]: Can and should 'upgrade' to a Collection type:
                TypeFactory tf = ctxt.getTypeFactory();
JavaType elemType = ((type.containedTypeCount() > 0))?type.containedType(1):TypeFactory.containedType(type);
                CollectionType ct = tf.constructCollectionType(Collection.class, elemType);
                // Should we re-introspect beanDesc? For now let's not...
                return createCollectionDeserializer(ctxt, ct, beanDesc);
            }
            // Primitives/wrappers, other Numbers:
            JsonDeserializer<?> deser = NumberDeserializers.find(rawType, clsName);
            if (deser == null) {
                deser = DateDeserializers.find(rawType, clsName);
                if (deser == null) {
                    deser = JdkDeserializers.find(rawType, clsName);
                }
            }
            return deser;
        }
        if (clsName.startsWith("com.fasterxml.")) {
            // and a few Jackson types as well:
            return JacksonDeserializers.find(rawType);
        }
        return null;
    }