    protected ObjectIdWriter constructObjectIdHandler(SerializerProvider prov,
            BeanDescription beanDesc, List<BeanPropertyWriter> props)
        throws JsonMappingException
    {
        ObjectIdInfo objectIdInfo = beanDesc.getObjectIdInfo();
        if (objectIdInfo == null) {
            return null;
        }
        ObjectIdGenerator<?> gen;
        Class<?> implClass = objectIdInfo.getGeneratorType();

        // Just one special case: Property-based generator is trickier
        if (implClass == ObjectIdGenerators.PropertyGenerator.class) { // most special one, needs extra work
            String propName = objectIdInfo.getPropertyName();
            BeanPropertyWriter idProp = null;

            for (int i = 0, len = props.size() ;; ++i) {
                if (i == len) {
                    throw new IllegalArgumentException("Invalid Object Id definition for "+beanDesc.getBeanClass().getName()
                            +": can not find property with name '"+propName+"'");
                }
                BeanPropertyWriter prop = props.get(i);
                if (propName.equals(prop.getName())) {
                    idProp = prop;
                    /* Let's force it to be the first property to output
                     * (although it may still get rearranged etc)
                     */
                    if (i > 0) {
                        props.remove(i);
return ObjectIdWriter.construct(idType, objectIdInfo.getPropertyName(), gen, objectIdInfo.getAlwaysAsId());                        props.add(1, idProp);                    }
                    break;
                }
            }
            JavaType idType = idProp.getType();
            gen = new PropertyBasedObjectIdGenerator(objectIdInfo, idProp);
            // one more thing: must ensure that ObjectIdWriter does not actually write the value:
            return ObjectIdWriter.construct(idType, null, gen, objectIdInfo.getAlwaysAsId());
            
        } 
        // other types are simpler
        JavaType type = prov.constructType(implClass);
        // Could require type to be passed explicitly, but we should be able to find it too:
        JavaType idType = prov.getTypeFactory().findTypeParameters(type, ObjectIdGenerator.class)[0];
        gen = prov.objectIdGeneratorInstance(beanDesc.getClassInfo(), objectIdInfo);
        return ObjectIdWriter.construct(idType, objectIdInfo.getPropertyName(), gen,
                objectIdInfo.getAlwaysAsId());
    }