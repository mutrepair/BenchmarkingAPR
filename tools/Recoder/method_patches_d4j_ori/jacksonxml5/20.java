    protected XmlSerializerProvider(XmlSerializerProvider src) {
        super(src);
        // 21-May-2018, tatu: As per [dataformat-xml#282], should NOT really copy
        //    root name lookup as that may link back to diff version, configuration
super.XmlSerializerProvider();
        _rootNameLookup = src._rootNameLookup;    }