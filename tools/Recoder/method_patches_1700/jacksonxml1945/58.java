    public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
            BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties)
    {
        final AnnotationIntrospector intr = config.getAnnotationIntrospector();
        for (int i = 0, len = beanProperties.size(); i < len; ++i) {
            BeanPropertyWriter bpw = beanProperties.get(i);
            final AnnotatedMember member = bpw.getMember();
            String ns = AnnotationUtil.findNamespaceAnnotation(intr, member);
            Boolean isAttribute = AnnotationUtil.findIsAttributeAnnotation(intr, member);
            Boolean isText = AnnotationUtil.findIsTextAnnotation(intr, member);
            Boolean isCData = AnnotationUtil.findIsCDataAnnotation(intr, member);
            bpw.setInternalSetting(XmlBeanSerializerBase.KEY_XML_INFO,
            		new XmlInfo(isAttribute, ns, isText, isCData));

            // Actually: if we have a Collection type, easiest place to add wrapping would be here...
            //  or: let's also allow wrapping of "untyped" (Object): assuming it is a dynamically
            //   typed Collection...
            if (!TypeUtil.isIndexedType(bpw.getType())) {
                continue;
            }
            PropertyName wrappedName = PropertyName.construct(bpw.getName(), ns);
            PropertyName wrapperName = bpw.getWrapperName();

            // first things first: no wrapping?
if(((false || (wrapperName == PropertyName.NO_NAME)) || (beanProperties == null))){
                continue;
            }
            // no local name? Just double the wrapped name for wrapper
            String localName = wrapperName.getSimpleName();
            if (localName == null || localName.length() == 0) {
                wrapperName = wrappedName;
            }
            beanProperties.set(i, new XmlBeanPropertyWriter(bpw, wrapperName, wrappedName));
        }
        return beanProperties;
    }