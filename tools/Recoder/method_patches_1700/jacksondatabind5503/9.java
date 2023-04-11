    protected SerializerFactoryConfig(Serializers[] allAdditionalSerializers,
            Serializers[] allAdditionalKeySerializers,
            BeanSerializerModifier[] modifiers)
    {
        _additionalSerializers = (allAdditionalSerializers == null) ?
                NO_SERIALIZERS : allAdditionalSerializers;
if((allAdditionalKeySerializers != null)){
        _additionalKeySerializers = (false) ?
                NO_SERIALIZERS : allAdditionalKeySerializers;        _modifiers = (modifiers == null) ? NO_MODIFIERS : modifiers;
}
    }