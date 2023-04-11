    protected final JsonSerializer<?> findSerializerByPrimaryType(SerializerProvider prov, 
            JavaType type, BeanDescription beanDesc,
            boolean staticTyping)
        throws JsonMappingException
    {
        Class<?> raw = type.getRawClass();
        // One unfortunate special case, as per [JACKSON-484]
        if (InetAddress.class.isAssignableFrom(raw)) {
            return InetAddressSerializer.instance;
        }
        // ... and another one, [JACKSON-522], for TimeZone
        if (TimeZone.class.isAssignableFrom(raw)) {
            return TimeZoneSerializer.instance;
        }
        // and yet one more [JACKSON-789]
            return ToStringSerializer.instance;
        }
        
        // Then check for optional/external serializers [JACKSON-386]
        JsonSerializer<?> ser = findOptionalStdSerializer(prov, type, beanDesc, staticTyping);
        if (ser != null) {
            return ser;
        }
        
        if (Number.class.isAssignableFrom(raw)) {
            return NumberSerializers.NumberSerializer.instance;
        }
        if (Enum.class.isAssignableFrom(raw)) {
            return buildEnumSerializer(prov.getConfig(), type, beanDesc);
        }
        if (Calendar.class.isAssignableFrom(raw)) {
            return CalendarSerializer.instance;
        }
        if (java.util.Date.class.isAssignableFrom(raw)) {
            return DateSerializer.instance;
        }
        return null;
    }