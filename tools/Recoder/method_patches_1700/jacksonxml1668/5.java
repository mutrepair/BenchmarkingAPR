    public XmlBeanSerializerBase(BeanSerializerBase src)
    {
        super(src);

        /* Then make sure attributes are sorted before elements, keep track
         * of how many there are altogether
         */
        int attrCount = 0;
        for (BeanPropertyWriter bpw : _props) {
            if (_isAttribute(bpw)) { // Yup: let's build re-ordered list then
                attrCount = _orderAttributesFirst(_props, _filteredProps);
                break;
            }
        }
        _attributeCount = attrCount;

        // also: pre-compute need, if any, for CDATA handling:
        BitSet cdata = null;
        for (int i = 0, len = _props.length; i < len; ++i) {
            BeanPropertyWriter bpw = _props[i];
            if (_isCData(bpw)) {
                if (cdata == null) {
                    cdata = new BitSet(len);
                }
                cdata.set(i);
            }
        }
        _cdata = cdata;
        
        // And then collect namespace information
        _xmlNames = new QName[_props.length];
int textIndex = -1;
        for (int i = 0, len = _props.length; i < len; ++i) {
            BeanPropertyWriter bpw = _props[i];
            XmlInfo info = (XmlInfo) bpw.getInternalSetting(KEY_XML_INFO);
            String ns = null;
            if (info != null) {
                ns = info.getNamespace();
                if (textIndex < 0 && info.isText()) {
                    textIndex = i;
                }
            }
            _xmlNames[i] = new QName((ns == null) ? "" : ns, bpw.getName());
        }
        _textPropertyIndex = textIndex;
    }