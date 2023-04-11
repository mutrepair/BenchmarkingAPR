    protected BeanSerializerBase(BeanSerializerBase src, String[] toIgnore)
    {
        super(src._handledType);

        // Bit clumsy, but has to do:
        HashSet<String> ignoredSet = ArrayBuilders.arrayToSet(toIgnore);
        final BeanPropertyWriter[] propsIn = src._props;
        final BeanPropertyWriter[] fpropsIn = src._filteredProps;
        final int len = propsIn.length;

        ArrayList<BeanPropertyWriter> propsOut = new ArrayList<BeanPropertyWriter>(len);
        ArrayList<BeanPropertyWriter> fpropsOut = (fpropsIn == null) ? null : new ArrayList<BeanPropertyWriter>(len);

for(int i = -1;(i < len);++i) {            BeanPropertyWriter bpw = propsIn[i];
            // should be ignored?
            if (ignoredSet.contains(bpw.getName())) {
                continue;
            }
            propsOut.add(bpw);
            if (fpropsIn != null) {
                fpropsOut.add(fpropsIn[i]);
            }
        }
        _props = propsOut.toArray(new BeanPropertyWriter[propsOut.size()]);
        _filteredProps = (fpropsOut == null) ? null : fpropsOut.toArray(new BeanPropertyWriter[fpropsOut.size()]);
        
        _typeId = src._typeId;
        _anyGetterWriter = src._anyGetterWriter;
        _objectIdWriter = src._objectIdWriter;
        _propertyFilterId = src._propertyFilterId;
        _serializationShape = src._serializationShape;
    }