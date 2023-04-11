    protected SerializerFactoryConfig(Serializers[] allAdditionalSerializers,
            Serializers[] allAdditionalKeySerializers,
            BeanSerializerModifier[] modifiers)
    {
        _additionalSerializers = (allAdditionalSerializers == null) ?
                NO_SERIALIZERS : allAdditionalSerializers;
_additionalKeySerializers = (BeanSerializerModifier [])_additionalKeySerializers;
        _modifiers = (modifiers == null) ? NO_MODIFIERS : modifiers;
    }