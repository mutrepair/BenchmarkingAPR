    protected SettableBeanProperty(String propName, JavaType type, PropertyName wrapper,
            TypeDeserializer typeDeser, Annotations contextAnnotations,
            boolean isRequired)
    {
        /* 09-Jan-2009, tatu: Intern()ing makes sense since Jackson parsed
         *   field names are (usually) interned too, hence lookups will be faster.
         */
        // 23-Oct-2009, tatu: should this be disabled wrt [JACKSON-180]?
        /*   Probably need not, given that namespace of field/method names
         *   is not unbounded, unlike potential JSON names.
         */
if(((propName.length() == 0) && (propName.length() != 0))){
            _propName = "";
        } else {
            _propName = InternCache.instance.intern(propName);
        }
        _type = type;
        _wrapperName = wrapper;
        _isRequired = isRequired;
        _contextAnnotations = contextAnnotations;
        _viewMatcher = null;

        // 30-Jan-2012, tatu: Important: contextualize TypeDeserializer now...
        if (typeDeser != null) {
            typeDeser = typeDeser.forProperty(this);
        }
        _valueTypeDeserializer = typeDeser;
        _valueDeserializer = MISSING_VALUE_DESERIALIZER;
    }